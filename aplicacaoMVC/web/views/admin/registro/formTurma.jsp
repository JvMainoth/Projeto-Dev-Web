<!--Criado por Oliver Almeida-->
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidade.Professor" %>
<%@ page import="model.ProfessorDAO" %>
<%@ page import="entidade.Disciplina" %>
<%@ page import="model.DisciplinaDAO" %>
<%@ page import="entidade.Aluno" %>
<%@ page import="model.AlunoDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Registrar Turma</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Registro de Turma</h3>

                <!-- Mensagem de erro -->
                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if (msgError != null && !msgError.isEmpty()) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%= msgError %>
                </div>
                <% } %>

                <!-- Formulário -->
                <form action="<%= request.getContextPath() %>/admin/RegistroController" method="POST">
                    <input type="hidden" name="action" value="cadastrarTurma">
                    <div class="mb-3">
                        <label for="codigo" class="form-label">Código da Turma</label>
                        <input type="text" id="codigo" name="codigo" class="form-control">
                    </div>
                    
                    <!-- Seleção de Professor -->
                    <div class="mb-3">
                        <label for="professor" class="form-label">Escolha um Professor:</label>
                        <select id="professor" name="idProfessor" class="form-select">
                            <option value="" disabled selected>Selecione um professor</option>
                            <%
                                try {
                                    ProfessorDAO professorDAO = new ProfessorDAO();
                                    ArrayList<Professor> professores = professorDAO.listarProfessores();

                                    for (Professor professor : professores) {
                            %>
                            <option value="<%= professor.getId() %>"><%= professor.getNome() %></option>
                            <%
                                    }
                                } catch (Exception e) {
                            %>
                            <option value="" disabled>Erro ao carregar professores</option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    
                    <!-- Seleção de Disciplina -->
                    <div class="mb-3">
                        <label for="disciplina" class="form-label">Escolha uma Disciplina:</label>
                        <select id="disciplina" name="idDisciplina" class="form-select">
                            <option value="" disabled selected>Selecione uma disciplina</option>
                            <%
                                try {
                                    DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                                    ArrayList<Disciplina> disciplinas = disciplinaDAO.listarDisciplinas();

                                    for (Disciplina disciplina : disciplinas) {
                            %>
                            <option value="<%= disciplina.getId() %>"><%= disciplina.getNome() %></option>
                            <%
                                    }
                                } catch (Exception e) {
                            %>
                            <option value="" disabled>Erro ao carregar disciplinas</option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    
                    <!-- Seleção de Aluno -->
                    <div class="mb-3">
                        <label for="aluno" class="form-label">Escolha um Aluno:</label>
                        <select id="aluno" name="idAluno" class="form-select">
                            <option value="" disabled selected>Selecione um aluno</option>
                            <%
                                try {
                                    AlunoDAO alunoDAO = new AlunoDAO();
                                    ArrayList<Aluno> alunos = alunoDAO.listarAlunos();

                                    for (Aluno aluno : alunos) {
                            %>
                            <option value="<%= aluno.getId() %>"><%= aluno.getNome() %></option>
                            <%
                                    }
                                } catch (Exception e) {
                            %>
                            <option value="" disabled>Erro ao carregar alunos</option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    
                    <div class="mb-3">
                        <label for="nota" class="form-label">Nota</label>
                        <input type="text" id="nota" name="nota" class="form-control">
                    </div>      
                    
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Enviar" class="btn btn-primary">  
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="../views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>