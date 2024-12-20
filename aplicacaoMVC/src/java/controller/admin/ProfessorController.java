/*Feito por João Mainoth e Oliver Almeida*/
package controller.admin;

import entidade.Professor;
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
import model.ProfessorDAO;

@WebServlet(name = "ProfessorController", urlPatterns = {"/admin/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            ProfessorDAO professorDAO = new ProfessorDAO();
            RequestDispatcher rd;
            
            switch (acao) {
                case "Listar":
                    ArrayList<Professor> listaProfessores = null;
                    try {
                        listaProfessores = professorDAO.listarProfessores();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("listaProfessores", listaProfessores);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaProfessores.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Alterar":
                    int idAlterar = Integer.parseInt(request.getParameter("id"));
                    Professor professorAlterar = null;
                    try {
                        professorAlterar = professorDAO.getProfessor(idAlterar);
                    } catch (Exception ex) {
                        Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("professor", professorAlterar);
                    rd = request.getRequestDispatcher("/views/admin/edicao/formProfessor.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    try {
                        Professor professor = professorDAO.getProfessor(idExcluir); // Recupera o aluno
                        professorDAO.excluir(professor); // Exclui o aluno
                        request.setAttribute("msgSuccess", "Professor excluído com sucesso!");
                    } catch (Exception e) {
                        request.setAttribute("msgError", "Erro ao excluir professor: " + e.getMessage());
                    }
                    // Após a exclusão, redireciona para a lista de alunos
                    listaProfessores = professorDAO.listarProfessores();
                    request.setAttribute("listaProfessores", listaProfessores);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaProfessores.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Incluir":
                    Professor professorIncluir = new Professor();
                    request.setAttribute("professor", professorIncluir);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/registro/formProfessor.jsp");
                    rd.forward(request, response);
                    break;
                    
                default:
                    response.sendRedirect("/aplicacaoMVC/admin/ProfessorController?acao=Listar");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("editarProfessor".equals(action)) {
            // Recupera os dados do formulário
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");

            // Cria o objeto Aluno
            Professor professor = new Professor();
            professor.setId(id);
            professor.setNome(nome);
            professor.setEmail(email);
            professor.setCpf(cpf);
            professor.setSenha(senha);

            ProfessorDAO professorDAO = new ProfessorDAO();
            try {
                // Registra o aluno no banco de dados
                professorDAO.alterar(professor);
                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "Professor editado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao editar professor: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }  else if ("excluirProfessor".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            ProfessorDAO professorDAO = new ProfessorDAO();
            try {
                Professor professor = professorDAO.getProfessor(id); // Recupera o aluno
                professorDAO.excluir(professor); // Exclui o aluno
                request.setAttribute("msgSuccess", "Professor excluído com sucesso!");
            } catch (Exception e) {
                request.setAttribute("msgError", "Erro ao excluir professor: " + e.getMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }
    }
}