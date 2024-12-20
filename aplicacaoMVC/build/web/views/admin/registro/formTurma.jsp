<!--Criado por João Mainoth-->
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
                    
                    <div class="mb-3">
                        <label for="id_professor" class="form-label">ID do Professor</label>
                        <input type="text" id="id_professor" name="id_professor" class="form-control">
                    </div>
                    
                    <div class="mb-3">
                        <label for="id_disciplina" class="form-label">ID da Disciplina</label>
                        <input type="text" id="id_disciplina" name="id_disciplina" class="form-control">
                    </div>
                    
                    <div class="mb-3">
                        <label for="id_aluno" class="form-label">ID do Aluno</label>
                        <input type="text" id="id_aluno" name="id_aluno" class="form-control">
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