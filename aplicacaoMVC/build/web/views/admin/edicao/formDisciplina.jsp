<%@ page import="entidade.Disciplina" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Editar Disciplina</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Edição de Disciplina</h3>

                <%
                    String msgError = (String) request.getAttribute("msgError");
                    if (msgError != null && !msgError.isEmpty()) {
                %>
                <div class="alert alert-danger" role="alert">
                    <%= msgError %>
                </div>
                <% } %>

                <!-- Formulário -->
                <form action="<%= request.getContextPath() %>/admin/DisciplinaController" method="POST">
                    <%
                        Disciplina disciplina = (Disciplina) request.getAttribute("disciplina");
                        if (disciplina != null) {
                    %>
                    <input type="hidden" name="action" value="editarDisciplina">
                    <input type="hidden" name="id" value="<%= disciplina.getId() %>">

                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome da disciplina" 
                               value="<%= disciplina.getNome() != null ? disciplina.getNome() : "" %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="requisito" class="form-label">Requisito</label>
                        <input type="text" id="requisito" name="requisito" class="form-control" placeholder="Requisito da disciplina" 
                               value="<%= disciplina.getRequisito() != null ? disciplina.getRequisito() : "" %>" required>
                    </div>

                    <div class="mb-3">
                        <label for="ementa" class="form-label">Ementa</label>
                        <textarea id="ementa" name="ementa" class="form-control" rows="4" placeholder="Descrição da ementa" required><%= 
                            disciplina.getEmenta() != null ? disciplina.getEmenta() : "" %></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Carga Horária</label>
                        <input type="number" id="carga_horaria" name="carga_horaria" class="form-control" placeholder="Horas" 
                               value="<%= disciplina.getCarga_horaria() != null ? disciplina.getCarga_horaria() : "" %>" required> 
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
