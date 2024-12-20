<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Registrar Disciplina</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Registro de Disciplina</h3>

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
                    <input type="hidden" name="action" value="cadastrarDisciplina">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome da disciplina" required>
                    </div>

                    <div class="mb-3">
                        <label for="requisito" class="form-label">Requisito</label>
                        <input type="text" id="requisito" name="requisito" class="form-control" placeholder="Requisito da disciplina" required>
                    </div>

                    <div class="mb-3">
                        <label for="ementa" class="form-label">Ementa</label>
                        <textarea id="ementa" name="ementa" class="form-control" rows="4" placeholder="Descrição da ementa" required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="cargaHoraria" class="form-label">Carga Horária</label>
                        <input type="number" id="cargaHoraria" name="cargaHoraria" class="form-control" placeholder="Horas" required>
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
