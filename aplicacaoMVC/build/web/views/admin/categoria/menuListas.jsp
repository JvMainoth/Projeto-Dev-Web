<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Menu de Listagem</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h3>Menu de Listagem</h3>
                <h4>Escolha o tipo de entidade para listar</h4>
                <div class="row mt-4">
                    <div class="col-md-2">
                        <a href="<%= request.getContextPath() %>/admin/ListaController?action=alunos" class="btn btn-primary btn-block">Listar Alunos</a>
                    </div>
                    <div class="col-md-2">
                        <a href="<%= request.getContextPath() %>/admin/ListaController?action=professores" class="btn btn-success btn-block">Listar Professores</a>
                    </div>
                    <div class="col-md-2">
                        <a href="<%= request.getContextPath() %>/admin/ListaController?action=administradores" class="btn btn-warning btn-block">Listar Administradores</a>
                    </div>
                    <div class="col-md-2">
                        <a href="<%= request.getContextPath() %>/admin/ListaController?action=disciplinas" class="btn btn-info btn-block">Listar Disciplinas</a>
                    </div>
                    <div class="col-md-2">
                        <a href="<%= request.getContextPath() %>/admin/ListaController?action=turmas" class="btn btn-danger btn-block">Listar Turmas</a>
                    </div>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>