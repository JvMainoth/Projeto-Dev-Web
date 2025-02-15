<%@page import="entidade.Turma"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista de Turmas</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">
                <h1>Área Restrita</h1>
                <h2>Lista de Turmas</h2>

                <a href="/aplicacaoMVC/admin/TurmaController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Professor_id</th>
                                <th scope="col">Disciplina_id</th>
                                <th scope="col">Aluno_id</th>
                                <th scope="col">Código Turma</th>
                                <th scope="col">Nota</th>
                                <th scope="col">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                // Obtem a lista de alunos do atributo definido pelo controller
                                ArrayList<Turma> listaTurmas = (ArrayList<Turma>) request.getAttribute("listaTurmas");
                                
                                for (Turma turma : listaTurmas) {
                                    out.println("<tr>");
                                    out.println("<th>" + turma.getId() + "</th>");
                                    out.println("<td>" + turma.getProfessor_id() + "</td>");
                                    out.println("<td>" + turma.getDisciplina_id() + "</td>");
                                    out.println("<td>" + turma.getAluno_id() + "</td>");
                                    out.println("<td>" + turma.getCodigo() + "</td>");
                                    out.println("<td>" + turma.getNota() + "</td>");
                                    
                                    %>
                            <td>
                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Alterar&id=<%=turma.getId()%>" class="btn btn-warning">Alterar</a>
                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Excluir&id=<%=turma.getId()%>" class="btn btn-danger">Excluir</a>
                            </td>
                            <%
                                    out.println("</tr>");
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>