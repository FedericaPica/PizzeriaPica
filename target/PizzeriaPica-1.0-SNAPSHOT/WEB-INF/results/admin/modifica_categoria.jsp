<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <title>Modifica categoria</title>
    <style>
        table {
            max-width: 500px;
            margin: auto;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
        }
        table td, table th {
            border: 1px solid black;
        }
    </style>
</head>
<body>
        <form action="ModificaCategoriaDBServlet">
            <table>
                <tr>
                    <td><label for="nomeId"> Nome: </label></td>
                    <td><input type="text" id="nomeId" name="nome" value="${categoria.nome}"></td>
                </tr>
                <tr>
                    <td><label for="priorityId"> Priorit&agrave;: </label></td>
                    <td><input type="number" id="priorityId" name="priority" value="${categoria.priority}"></td>
                </tr>
                <tr>
                    <td style="width: 100%; border:none; text-align: right"><input type="submit" value="Salva Modifica"></td>
                    <input type="hidden" name="id" value="${categoria.id}">
                </tr>
            </table>
        </form>
        <script>
            window.onunload = function(){
                window.opener.location.reload();
                window.close();
            };
        </script>
</body>
</html>
