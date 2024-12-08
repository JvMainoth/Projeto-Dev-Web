<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Registrar Professor</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Registro de Professor</h3>

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
                <form action="${pageContext.request.contextPath}/admin/RegistroController?action=professores" method="POST">
                    <!-- Nome -->
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome completo" required>
                    </div>

                    <!-- E-mail -->
                    <div class="mb-3">
                        <label for="email" class="form-label">E-mail</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="exemplo@dominio.com" required>
                    </div>

                    <!-- CPF -->
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" id="cpf" name="cpf" class="form-control" placeholder="999.999.999-99" required>
                    </div>

                    <!-- Senha -->
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" id="senha" name="senha" class="form-control" placeholder="Digite uma senha" required>
                    </div>

                    <!-- Botão de Envio -->
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
