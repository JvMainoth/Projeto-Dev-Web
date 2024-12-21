/*Criado por João Mainoth e Oliver Almeida*/
package controller.admin;

import entidade.Aluno;
import model.AlunoDAO;

import entidade.Professor;
import model.ProfessorDAO;

import entidade.Administrador;
import model.AdministradorDAO;

import entidade.Disciplina;
import model.DisciplinaDAO;

import entidade.Turma;
import model.TurmaDAO;

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
                case "turmas":
                    page = "/views/admin/registro/formTurma.jsp";
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
            
        } else if ("cadastrarProfessor".equals(action)) {
            // Recupera os dados do formulário
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            
            // Cria o objeto Professor
            Professor professor = new Professor();
            professor.setNome(nome);
            professor.setEmail(email);
            professor.setCpf(cpf);
            professor.setSenha(senha);
            
            ProfessorDAO professorDAO = new ProfessorDAO();
            /*Rever essas exceções e casos*/
            try {
                // Registra o professor no banco de dados
                professorDAO.inserir(professor);

                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "Professor cadastrado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao cadastrar Professor: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/registro/menuRegistro.jsp");
            rd.forward(request, response);
            
        } else if ("cadastrarAdministrador".equals(action)) {
            // Recupera os dados do formulário
            
            // Não recupera se o administrador é aprovado ou não -> resolver isso
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            String aprovado = request.getParameter("aprovado");
            String endereco = request.getParameter("endereco");
            
            Administrador administrador = new Administrador();
            administrador.setNome(nome);
            administrador.setCpf(cpf);
            administrador.setSenha(senha);
            administrador.setAprovado(aprovado);
            administrador.setEndereco(endereco);
            
            AdministradorDAO administradorDAO = new AdministradorDAO();
            /*Rever essas exceções e casos*/
            try {
                // Registra o administrador no banco de dados
                administradorDAO.Inserir(administrador);

                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "administrador cadastrado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao cadastrar Administrador: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/registro/menuRegistro.jsp");
            rd.forward(request, response);
            
        } else if ("cadastrarDisciplina".equals(action)) {
            String nome = request.getParameter("nome");
            String requisito = request.getParameter("requisito");
            String ementa = request.getParameter("ementa");
            Short carga_horaria = Short.parseShort(request.getParameter("cargaHoraria"));
            
            Disciplina disciplina = new Disciplina();
            disciplina.setNome(nome);
            disciplina.setRequisito(requisito);
            disciplina.setEmenta(ementa);
            disciplina.setCarga_horaria(carga_horaria);
            
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            /*Rever essas exceções e casos*/
            try {
                // Registra a disciplina no banco de dados
                disciplinaDAO.inserir(disciplina);

                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "disciplina cadastrado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao cadastrar disciplina: " + e.getMessage());
            }

            // Redireciona para a página de menu ou feedback
            RequestDispatcher rd = request.getRequestDispatcher("/views/admin/registro/menuRegistro.jsp");
            rd.forward(request, response);
            
        } else if ("cadastrarTurma".equals(action)) {
            
            int professor = Integer.parseInt(request.getParameter("idProfessor"));
            int disciplina = Integer.parseInt(request.getParameter("idDisciplina"));
            int aluno = Integer.parseInt(request.getParameter("idAluno"));
            String codigo = request.getParameter("codigo");
            int nota = Integer.parseInt(request.getParameter("nota"));
            
            Turma turma = new Turma();
            turma.setProfessor_id(professor);
            turma.setDisciplina_id(disciplina);
            turma.setAluno_id(aluno);
            turma.setCodigo(codigo);
            turma.setNota(nota);
            
            TurmaDAO turmaDAO = new TurmaDAO();
            /*Rever essas exceções e casos*/
            try {
                // Registra a disciplina no banco de dados
                turmaDAO.inserir(turma);

                // Define mensagem de sucesso
                request.setAttribute("msgSuccess", "disciplina cadastrado com sucesso!");
            } catch (Exception e) {
                // Define mensagem de erro
                request.setAttribute("msgError", "Erro ao cadastrar disciplina: " + e.getMessage());
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