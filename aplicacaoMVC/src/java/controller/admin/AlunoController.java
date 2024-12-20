/*Feito por João Mainoth e Oliver Almeida*/
package controller.admin;

import entidade.Aluno;
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
import model.AlunoDAO;

@WebServlet(name = "AlunoController", urlPatterns = {"/admin/AlunoController"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            AlunoDAO alunoDAO = new AlunoDAO();
            RequestDispatcher rd;
            
            switch (acao) {
                case "Listar":
                    ArrayList<Aluno> listaAlunos = null;
                    try {
                        listaAlunos = alunoDAO.listarAlunos();
                    } catch (SQLException ex) {
                        Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("listaAlunos", listaAlunos);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaAlunos.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Alterar":
                    int idAlterar = Integer.parseInt(request.getParameter("id"));
                    Aluno alunoAlterar = null;
                    try {
                        alunoAlterar = alunoDAO.getAluno(idAlterar);
                    } catch (Exception ex) {
                        Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("aluno", alunoAlterar);
                    rd = request.getRequestDispatcher("/views/admin/edicao/formAluno.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Excluir":
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    try {
                        Aluno aluno = alunoDAO.getAluno(idExcluir); // Recupera o aluno
                        alunoDAO.excluir(aluno); // Exclui o aluno
                        request.setAttribute("msgSuccess", "Aluno excluído com sucesso!");
                    } catch (Exception e) {
                        request.setAttribute("msgError", "Erro ao excluir aluno: " + e.getMessage());
                    }
                    // Após a exclusão, redireciona para a lista de alunos
                    listaAlunos = alunoDAO.listarAlunos();
                    request.setAttribute("listaAlunos", listaAlunos);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaAlunos.jsp");
                    rd.forward(request, response);
                    break;
                    
                case "Incluir":
                    Aluno alunoIncluir = new Aluno();
                    request.setAttribute("aluno", alunoIncluir);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/registro/formAluno.jsp");
                    rd.forward(request, response);
                    break;
                    
                default:
                    response.sendRedirect("/aplicacaoMVC/admin/AlunoController?acao=Listar");
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("editarAluno".equals(action)) {
            // Recupera os dados do formulário
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String celular = request.getParameter("celular");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String endereco = request.getParameter("endereco");
            String cidade = request.getParameter("cidade");
            String bairro = request.getParameter("bairro");
            String cep = request.getParameter("cep");

            // Cria o objeto Aluno
            Aluno aluno = new Aluno();
            aluno.setId(id);
            aluno.setNome(nome);
            aluno.setEmail(email);
            aluno.setCelular(celular);
            aluno.setCpf(cpf);
            aluno.setSenha(senha);
            aluno.setEndereco(endereco);
            aluno.setCidade(cidade);
            aluno.setBairro(bairro);
            aluno.setCep(cep);

            AlunoDAO alunoDAO = new AlunoDAO();
            try {
                // Registra o aluno no banco de dados
                alunoDAO.alterar(aluno);
                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "Aluno editado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao editar aluno: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }  else if ("excluirAluno".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));

            AlunoDAO alunoDAO = new AlunoDAO();
            try {
                Aluno aluno = alunoDAO.getAluno(id); // Recupera o aluno
                alunoDAO.excluir(aluno); // Exclui o aluno
                request.setAttribute("msgSuccess", "Aluno excluído com sucesso!");
            } catch (Exception e) {
                request.setAttribute("msgError", "Erro ao excluir aluno: " + e.getMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }
    }
}