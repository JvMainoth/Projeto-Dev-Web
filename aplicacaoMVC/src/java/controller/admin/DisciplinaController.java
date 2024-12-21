/*Feito por João Mainoth e Oliver Almeida*/
package controller.admin;

import entidade.Disciplina;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DisciplinaDAO;

@WebServlet(name = "DisciplinaController", urlPatterns = {"/admin/DisciplinaController"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            RequestDispatcher rd;
            
            switch (acao) {
                case "Listar":
                    ArrayList<Disciplina> listaDisciplinas = null;
                    try {
                        listaDisciplinas = disciplinaDAO.listarDisciplinas();
                    } catch (SQLException ex) {
                        Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("listaDisciplinas", listaDisciplinas);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaDisciplinas.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Alterar":
                    int idAlterar = Integer.parseInt(request.getParameter("id"));
                    Disciplina disciplinaAlterar = null;
                    try {
                        disciplinaAlterar = disciplinaDAO.getDisciplina(idAlterar);
                    } catch (Exception ex) {
                        Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("disciplina", disciplinaAlterar);
                    rd = request.getRequestDispatcher("/views/admin/edicao/formDisciplina.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    try {
                        Disciplina disciplina = disciplinaDAO.getDisciplina(idExcluir); // Recupera o aluno
                        disciplinaDAO.excluir(disciplina); // Exclui o aluno
                        request.setAttribute("msgSuccess", "Disciplina excluído com sucesso!");
                    } catch (Exception e) {
                        request.setAttribute("msgError", "Erro ao excluir disciplina: " + e.getMessage());
                    }
                    // Após a exclusão, redireciona para a lista de alunos
                    listaDisciplinas = disciplinaDAO.listarDisciplinas();
                    request.setAttribute("listaDisciplinas", listaDisciplinas);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaDisciplinas.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Incluir":
                    Disciplina disciplinaIncluir = new Disciplina();
                    request.setAttribute("disciplina", disciplinaIncluir);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/registro/formDisciplina.jsp");
                    rd.forward(request, response);
                    break;
                    
                default:
                    response.sendRedirect("/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplinaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("editarDisciplina".equals(action)) {
            // Recupera os dados do formulário
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String requisito = request.getParameter("requisito");
            String ementa = request.getParameter("ementa");
            short carga_horaria = Short.parseShort(request.getParameter("carga_horaria"));

            // Cria o objeto Aluno
            Disciplina disciplina = new Disciplina();
            disciplina.setId(id);
            disciplina.setNome(nome);
            disciplina.setRequisito(requisito);
            disciplina.setEmenta(ementa);
            disciplina.setCarga_horaria(carga_horaria);
            
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            try {
                // Registra o aluno no banco de dados
                disciplinaDAO.alterar(disciplina);
                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "Disciplina editado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao editar disciplina: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }  else if ("excluirDisciplina".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            try {
                Disciplina disciplina = disciplinaDAO.getDisciplina(id); // Recupera o aluno
                disciplinaDAO.excluir(disciplina); // Exclui o aluno
                request.setAttribute("msgSuccess", "Disciplina excluído com sucesso!");
            } catch (Exception e) {
                request.setAttribute("msgError", "Erro ao excluir disciplina: " + e.getMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }
    }
}
