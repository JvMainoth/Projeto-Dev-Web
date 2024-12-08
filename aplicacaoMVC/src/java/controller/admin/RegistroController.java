/*Criado por João Mainoth*/
package controller.admin;

import entidade.Aluno;
import model.AlunoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet responsável por exibir o menu de registros e processar os formulários correspondentes.
 * Agora também suporta o registro de alunos.
 */
@WebServlet(name = "RegistroController", urlPatterns = {"/admin/RegistroController"})
public class RegistroController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera o parâmetro para decidir qual página exibir
        String action = request.getParameter("action");

        String page; // Página para redirecionar
        if (action == null || action.isEmpty()) {
            // Menu principal se nenhum parâmetro for enviado
            page = "/views/admin/registro/menuRegistro.jsp";
        } else {
            // Determina qual formulário exibir com base no parâmetro
            switch (action) {
                case "alunos":
                    page = "/views/admin/registro/formAluno.jsp";
                    break;
                case "professores":
                    page = "/views/admin/registro/formProfessor.jsp";
                    break;
                case "administradores":
                    page = "/views/admin/registro/formAdministrador.jsp";
                    break;
                case "disciplinas":
                    page = "/views/admin/registro/formDisciplina.jsp";
                    break;
                default:
                    // Página de erro ou menu como fallback
                    page = "/views/admin/registro/menuRegistro.jsp";
                    break;
            }
        }

        // Redireciona para a página determinada
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera a ação enviada no formulário
        String action = request.getParameter("action");

        if ("cadastrarAluno".equals(action)) {
            // Recupera os dados do formulário
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
            /*Rever essas exceções e casos*/
            try {
                // Registra o aluno no banco de dados
                alunoDAO.inserir(aluno);

                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "Aluno cadastrado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao cadastrar aluno: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/registro/menuRegistro.jsp");
            rd.forward(request, response);
        } else {
            // Caso nenhuma ação seja correspondente, redireciona para o menu
            response.sendRedirect(request.getContextPath() + "/views/admin/registro/menuRegistro.jsp");
        }
    }
}