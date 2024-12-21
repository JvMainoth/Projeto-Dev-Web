/*Feito por João Mainoth e Oliver Almeida*/
package controller.admin;

import entidade.Turma;
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
import model.TurmaDAO;

@WebServlet(name = "TurmaController", urlPatterns = {"/admin/TurmaController"})
public class TurmaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            TurmaDAO turmaDAO = new TurmaDAO();
            RequestDispatcher rd;
            
            switch (acao) {
                case "Listar":
                    ArrayList<Turma> listaTurmas = null;
                    try {
                        listaTurmas = turmaDAO.listarTurmas();
                    } catch (SQLException ex) {
                        Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("listaTurmas", listaTurmas);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaTurmas.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Alterar":
                    int idAlterar = Integer.parseInt(request.getParameter("id"));
                    Turma turmaAlterar = null;
                    try {
                        turmaAlterar = turmaDAO.getTurma(idAlterar);
                    } catch (Exception ex) {
                        Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("turma", turmaAlterar);
                    rd = request.getRequestDispatcher("/views/admin/edicao/formTurmas.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    try {
                        Turma turma = turmaDAO.getTurma(idExcluir); // Recupera o aluno
                        turmaDAO.excluir(turma); // Exclui o aluno
                        request.setAttribute("msgSuccess", "turma excluído com sucesso!");
                    } catch (Exception e) {
                        request.setAttribute("msgError", "Erro ao excluir turma: " + e.getMessage());
                    }
                    // Após a exclusão, redireciona para a lista de alunos
                    listaTurmas = turmaDAO.listarTurmas();
                    request.setAttribute("listaTurmas", listaTurmas);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaTurmas.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Incluir":
                    Turma turmaIncluir = new Turma();
                    request.setAttribute("aluno", turmaIncluir);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/registro/formTurmas.jsp");
                    rd.forward(request, response);
                    break;
                    
                default:
                    response.sendRedirect("/aplicacaoMVC/admin/TurmaController?acao=Listar");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TurmaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("editarTurma".equals(action)) {
            // Recupera os dados do formulário
            int id = Integer.parseInt(request.getParameter("id"));
            int professor_id = Integer.parseInt(request.getParameter("professor_id"));
            int disciplina_id = Integer.parseInt(request.getParameter("disciplina_id"));
            int aluno_id = Integer.parseInt(request.getParameter("aluno_id"));
            String codigo_turma = request.getParameter("codigo_turma");
            int nota = Integer.parseInt(request.getParameter("nota"));

            // Cria o objeto Turma
            Turma turma = new Turma();
            turma.setId(id);
            turma.setProfessor_id(professor_id);
            turma.setDisciplina_id(disciplina_id);
            turma.setAluno_id(aluno_id);
            turma.setCodigo(codigo_turma);
            turma.setNota(nota);

            TurmaDAO turmaDAO = new TurmaDAO();
            try {
                // Registra o aluno no banco de dados
                turmaDAO.alterar(turma);
                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "Turma editada com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao editar turma: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }  else if ("excluirTurma".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            TurmaDAO turmaDAO = new TurmaDAO();
            try {
                Turma turma = turmaDAO.getTurma(id); // Recupera o aluno
                turmaDAO.excluir(turma); // Exclui o aluno
                request.setAttribute("msgSuccess", "Turma excluída com sucesso!");
            } catch (Exception e) {
                request.setAttribute("msgError", "Erro ao excluir turma: " + e.getMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }
    }
}