<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entidade.Aluno"%>

<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Gerenciar Aluno</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="row mt-5">
                <div class="col-sm-6 offset-3">
                    <%
                        Aluno aluno = (Aluno) request.getAttribute("aluno");
                        String acao = (String) request.getAttribute("acao");

                        switch (acao) {
                            case "Incluir":
                                out.println("<h1>Incluir Aluno</h1>");
                                break;
                            case "Alterar":
                                out.println("<h1>Alterar Aluno</h1>");
                                break;
                            case "Excluir":
                                out.println("<h1>Excluir Aluno</h1>");
                                break;
                        }

                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError %>
                    </div>
                    <% } %>

                    <form action="/aplicacaoMVC/admin/AlunoController" method="POST">
                        <input type="hidden" name="id" value="<%= aluno.getId() %>" class="form-control">

                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getNome() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="email" name="email" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getEmail() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="celular" class="form-label">Celular</label>
                            <input type="text" name="celular" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getCelular() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getCpf() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getSenha() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getEndereco() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" name="cidade" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getCidade() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" name="bairro" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getBairro() %>" class="form-control">
                        </div>

                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" <%= acao.equals("Excluir") ? "readonly" : "" %> value="<%= aluno.getCep() %>" class="form-control">
                        </div>

                        <div>
                            <input type="submit" name="btEnviar" value="<%= acao %>" class="btn btn-primary">
                            <a href="/aplicacaoMVC/admin/AlunoController?acao=Listar" class="btn btn-danger">Retornar</a>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>

</html>