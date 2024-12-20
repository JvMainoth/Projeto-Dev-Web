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

        String acao = request.getParameter("acao");
        Aluno aluno = new Aluno();
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
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
            {
                try {
                    aluno = alunoDAO.getAluno(id);
                } catch (Exception ex) {
                    Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/registro/formAluno.jsp");
                rd.forward(request, response);
                break;


            case "Incluir":
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/registro/formAluno.jsp");
                rd.forward(request, response);
                break;

            default:
                response.sendRedirect("/aplicacaoMVC/admin/AlunoController?acao=Listar");
                break;
        }
    }

    @Override //avaliar esta parte
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String btEnviar = request.getParameter("btEnviar");

        RequestDispatcher rd;

        if (nome.isEmpty() || email.isEmpty()) {
            Aluno aluno = new Aluno();
            if (btEnviar.equals("Alterar") || btEnviar.equals("Excluir")) {
                try {
                    AlunoDAO alunoDAO = new AlunoDAO();
                    aluno = alunoDAO.getAluno(id);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Erro ao buscar dados do aluno.");
                }
            }

            request.setAttribute("aluno", aluno);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
            rd.forward(request, response);

        } else {
            Aluno aluno = new Aluno(nome, email, celular, cpf, senha, endereco, cidade, bairro, cep);
            AlunoDAO alunoDAO = new AlunoDAO();

            try {
                switch (btEnviar) {
                    case "Incluir":
                        try{
                            alunoDAO.inserir(aluno);
                            request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        }catch(Exception ex){
                            System.out.println("Erro ao incluir aluno: " + ex.getMessage());  
                            request.setAttribute("msgError", "Erro ao incluir o aluno. Tente novamente.");
                            request.setAttribute("aluno", aluno);
                            rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                            rd.forward(request, response);
                            return;
                        }
                        break;

                    case "Alterar":
                        try {
                            alunoDAO.alterar(aluno);
                            request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        } catch (Exception ex) {
                            System.out.println("Erro ao alterar aluno: " + ex.getMessage());
                            request.setAttribute("msgError", "Erro ao alterar o aluno. Tente novamente.");
                            request.setAttribute("aluno", aluno);
                            rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                            rd.forward(request, response);
                            return;
                        }
                        break;

                    case "Excluir":
                        try {
                            alunoDAO.excluir(aluno);
                            request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        } catch (Exception ex) {
                            System.out.println("Erro ao excluir aluno: " + ex.getMessage());
                            request.setAttribute("msgError", "Erro ao excluir o aluno. Tente novamente.");
                            request.setAttribute("aluno", aluno);
                            rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                            rd.forward(request, response);
                            return; // Evitar continuar o fluxo após o erro
                        }
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/AlunoController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (IOException | ServletException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Erro ao executar operação com Aluno.");
            }
        }
    }
}