<!--Criado por João Mainoth-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Registrar Administrador</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Registro de Administrador</h3>

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
                <form action="${pageContext.request.contextPath}/admin/RegistroController" method="POST">
                    <input type="hidden" name="action" value="cadastrarAluno">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" id="nome" name="nome" class="form-control" placeholder="Seu nome">
                    </div>
                    
                    <div class="mb-3">
                        <label for="email" class="form-label">E-mail</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="Seu e-mail">
                    </div>
                    
                    <div class="mb-3">
                        <label for="celular" class="form-label">Celular</label>
                        <input type="text" id="celular" name="celular" class="form-control" placeholder="(21)99999-9999">
                    </div>
                    
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" id="cpf" name="cpf" class="form-control" placeholder="999.999.999-99">
                    </div>
                    
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" id="senha" name="senha" class="form-control" placeholder="Sua senha">
                    </div>
                    
                    <div class="mb-3">
                        <label for="endereco" class="form-label">Endereço</label>
                        <input type="text" id="endereco" name="endereco" class="form-control" placeholder="Seu endereço">
                    </div>
                    
                    <div class="mb-3">
                        <label for="cidade" class="form-label">Cidade</label>
                        <input type="text" id="cidade" name="cidade" class="form-control" placeholder="Sua cidade">
                    </div>
                    
                    <div class="mb-3">
                        <label for="bairro" class="form-label">Bairro</label>
                        <input type="text" id="bairro" name="bairro" class="form-control" placeholder="Seu bairro">
                    </div>
                    
                    <div class="mb-3">
                        <label for="cep" class="form-label">CEP</label>
                        <input type="text" id="cep" name="cep" class="form-control" placeholder="99999-999">
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