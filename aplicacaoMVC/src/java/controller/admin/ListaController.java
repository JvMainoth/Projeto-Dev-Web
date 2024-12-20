
package controller.admin;

import entidade.Aluno;
//import entidade.Professor;
import entidade.Administrador;
//import entidade.Disciplina;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlunoDAO;
//import model.ProfessorDAO;
import model.AdministradorDAO;
//import model.DisciplinaDAO;

@WebServlet(name = "ListaController", urlPatterns = {"/admin/ListaController"})
public class ListaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        String page = null; // Página para redirecionar
        if (action == null || action.isEmpty()) {
            // Menu principal se nenhum parâmetro for enviado
            page = "/views/admin/categoria/menuListas.jsp";
        } else {
            switch (action) {
                case "alunos":
                    page = "/admin/AlunoController?acao=Listar";
                    break;
                    
                    /*AlunoDAO alunoDAO = new AlunoDAO();
                    ArrayList<Aluno> listaAlunos = alunoDAO.ListaDeAlunos();
                    request.setAttribute("listaAlunos", listaAlunos);
                    rd = request.getRequestDispatcher("/views/admin/listaAlunos.jsp");
                    rd.forward(request, response);*/
                    

                case "professores":
                    page = "/admin/ProfessorController?acao=Listar";
                    /*ProfessorDAO professorDAO = new ProfessorDAO();
                    ArrayList<Professor> listaProfessores = professorDAO.getAll();
                    request.setAttribute("listaProfessores", listaProfessores);
                    rd = request.getRequestDispatcher("/views/admin/listaProfessores.jsp");
                    rd.forward(request, response);*/
                    break;

                case "administradores":
                    page = "/admin/AdministradorController?acao=Listar";
                    /*AdministradorDAO administradorDAO = new AdministradorDAO();
                    ArrayList<Administrador> listaAdministradores = administradorDAO.getAll();
                    request.setAttribute("listaAdministradores", listaAdministradores);
                    rd = request.getRequestDispatcher("/views/admin/listaAdministradores.jsp");
                    rd.forward(request, response);*/
                    break;

                case "disciplinas":
                    page = "/admin/DisciplinaController?acao=Listar";
                    /* DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                    ArrayList<Disciplina> listaDisciplinas = disciplinaDAO.getAll();
                    request.setAttribute("listaDisciplinas", listaDisciplinas);
                    rd = request.getRequestDispatcher("/views/admin/listaDisciplinas.jsp");
                    rd.forward(request, response);*/
                    break;
                
                case "turmas":
                    page = "/admin/TurmaController?acao=Listar";
                    break;

                default:
                    page = "/views/admin/categoria/menuListas.jsp";
                    break;
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
        
    }
}
       

    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}*/
