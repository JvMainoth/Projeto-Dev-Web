/*Feito por João Mainoth e Oliver Almeida*/
package controller.admin;

import entidade.Administrador;
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
import model.AdministradorDAO;

@WebServlet(name = "AdministradorController", urlPatterns = {"/admin/AdministradorController"})
public class AdministradorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String acao = request.getParameter("acao");
            AdministradorDAO administradorDAO = new AdministradorDAO();
            RequestDispatcher rd;

            switch (acao) {
                case "Listar":
                    ArrayList<Administrador> listaAdministradores = administradorDAO.ListaDeAdministrador();
                    request.setAttribute("listaDeAdministrador", listaAdministradores);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaAdministradores.jsp");
                    rd.forward(request, response);
                    break;

                case "Alterar":
                    int idAlterar = Integer.parseInt(request.getParameter("id"));
                    Administrador administradorAlterar = administradorDAO.getAdministrador(idAlterar);
                    request.setAttribute("administrador", administradorAlterar);
                    rd = request.getRequestDispatcher("/views/admin/edicao/formAdministrador.jsp");
                    rd.forward(request, response);
                    break;

                case "Excluir":
                    /*int idExcluir = Integer.parseInt(request.getParameter("id"));
                    Administrador administradorExcluir = administradorDAO.getAdministrador(idExcluir);
                    administradorDAO.Excluir(administradorExcluir);
                    request.setAttribute("msgSuccess", "Administrador excluído com sucesso!");
                    ArrayList<Administrador> listaAtualizada = administradorDAO.ListaDeAdministrador();
                    request.setAttribute("listaAdministradores", listaAtualizada);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaAdministradores.jsp");
                    rd.forward(request, response);
                    break;*/
                    int idExcluir = Integer.parseInt(request.getParameter("id"));
                    try {
                        Administrador administrador = administradorDAO.getAdministrador(idExcluir); // Recupera o aluno
                        administradorDAO.Excluir(administrador); // Exclui o aluno
                        request.setAttribute("msgSuccess", "Administrador excluído com sucesso!");
                    } catch (Exception e) {
                        request.setAttribute("msgError", "Erro ao excluir administrador: " + e.getMessage());
                    }
                    // Após a exclusão, redireciona para a lista de alunos
                    listaAdministradores = administradorDAO.ListaDeAdministrador();
                    request.setAttribute("listaDeAdministrador", listaAdministradores);
                    rd = request.getRequestDispatcher("/views/admin/categoria/listaAdministradores.jsp");
                    rd.forward(request, response);
                    break;

                case "Incluir":
                    Administrador administradorIncluir = new Administrador();
                    request.setAttribute("administrador", administradorIncluir);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", acao);
                    rd = request.getRequestDispatcher("/views/admin/registro/formAdministrador.jsp");
                    rd.forward(request, response);
                    break;

                default:
                    response.sendRedirect("/aplicacaoMVC/admin/AdministradorController?acao=Listar");
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(AdministradorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("editarAdministrador".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String endereco = request.getParameter("endereco");
            String senha = request.getParameter("senha");
            String aprovado = request.getParameter("aprovado");

            Administrador administrador = new Administrador();
            administrador.setId(id);
            administrador.setNome(nome);
            administrador.setCpf(cpf);
            administrador.setEndereco(endereco);
            administrador.setSenha(senha);
            administrador.setAprovado(aprovado);

            AdministradorDAO administradorDAO = new AdministradorDAO();
            try {
                administradorDAO.Alterar(administrador);
                request.setAttribute("msgSuccess", "Administrador editado com sucesso!");
            } catch (Exception e) {
                request.setAttribute("msgError", "Erro ao editar administrador: " + e.getMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);

        } else if ("excluirAdministrador".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            
            AdministradorDAO administradorDAO = new AdministradorDAO();
            try {
                Administrador administrador = administradorDAO.getAdministrador(id);
                administradorDAO.Excluir(administrador);
                request.setAttribute("msgSuccess", "Administrador excluído com sucesso!");
            } catch (Exception e) {
                request.setAttribute("msgError", "Erro ao excluir administrador: " + e.getMessage());
            }

            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/categoria/menuListas.jsp");
            rd.forward(request, response);
        }
    }
}