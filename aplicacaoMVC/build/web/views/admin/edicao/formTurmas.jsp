<%@ page import="entidade.Turma" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Editar Turma</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Edição de Turma</h3>

                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if (msgError != null && !msgError.isEmpty()) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%= msgError %>
                </div>
                <% } %>

                <!-- Formulário -->
                <form action="<%= request.getContextPath() %>/admin/TurmaController" method="POST">
                    <%
                        Turma turma = (Turma) request.getAttribute("turma");
                        if (turma !=null) {
                    %>
                    <input type="hidden" name="action" value="editarTurma">
                    <input type="hidden" name="id" value="<%= turma.getId() %>">

                    <div class="mb-3">
                        <label for="nome" class="form-label">professor_id</label>
                        <input type="number" id="professor_id" name="professor_id" class="form-control" placeholder="ID do professor" 
                               value="<%= turma.getProfessor_id() %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="requisito" class="form-label">disciplina_id</label>
                        <input type="number" id="disciplina_id" name="disciplina_id" class="form-control" placeholder="ID da disciplina" 
                               value="<%= turma.getDisciplina_id() %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="ementa" class="form-label">aluno_id</label>
                        <input type="number" id="disciplina_id" name="disciplina_id" class="form-control" placeholder="ID do aluno" 
                               value="<%= turma.getAluno_id() %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Código da Turma</label>
                        <input type=text id="codigo" name="codigo" class="form-control" 
                               value="<%= turma.getCodigo() %>" required> 
                    </div>
                    
                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Nota</label>
                        <input type="number" id="nota" name="nota" class="form-control"  
                               value="<%= turma.getNota() %>" required> 
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Enviar" class="btn btn-primary">
                        </div>
                    </div>
                    <% } else { %>
                    <p class="text-danger">Disciplina não encontrada.</p>
                    <% } %>
                </form>
            </div>
        </div>
        <script src="../views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

