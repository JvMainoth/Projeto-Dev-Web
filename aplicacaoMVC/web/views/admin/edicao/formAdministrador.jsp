<%@ page import="entidade.Administrador" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Editar Administrador</title>
        <link href="../views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="col-sm-6 offset-3 mt-5">
                <h3>Edição de Administrador</h3>

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
                <form action="<%= request.getContextPath() %>/admin/AdministradorController" method="POST">
                    <input type="hidden" name="action" value="editarAdministrador">
                    <% Administrador administrador = (Administrador) request.getAttribute("administrador"); %>
                    <input type="hidden" name="id" value="<%= administrador.getId() %>">

                    <!-- Nome -->
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" id="nome" name="nome" class="form-control" placeholder="Nome completo" value="<%= administrador.getNome() %>">
                    </div>
                  
                    <!-- CPF -->
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" id="cpf" name="cpf" class="form-control" placeholder="999.999.999-99" value="<%= administrador.getCpf() %>">
                    </div>
                    
                    <!-- Senha -->
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" id="senha" name="senha" class="form-control" placeholder="Digite uma senha" value="<%= administrador.getSenha() %>">
                    </div>
                    
                    <!-- Aprovado -->
                    <div class="mb-3">
                        <label for="aprovado" class="form-label">Aprovado</label>
                        <select id="aprovado" name="aprovado" class="form-select">
                            <option value="s" <%= "s".equals(administrador.getAprovado()) ? "selected" : "" %>>Sim</option>
                            <option value="n" <%= "n".equals(administrador.getAprovado()) ? "selected" : "" %>>Não</option>
                        </select>
                    </div>
                    
                    <!-- Endereço -->
                    <div class="mb-3">
                        <label for="endereco" class="form-label">Endereço</label>
                        <input type="text" id="endereco" name="endereco" class="form-control" placeholder="Endereço completo" value="<%= administrador.getEndereco() %>">
                    </div>
                    
                    <!-- Botões -->
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Salvar" class="btn btn-primary">
                        </div>
                        <div class="col-sm-2">
                            <a href="<%= request.getContextPath() %>/admin/AdministradorController?acao=Listar" class="btn btn-danger">Cancelar</a>
                        </
