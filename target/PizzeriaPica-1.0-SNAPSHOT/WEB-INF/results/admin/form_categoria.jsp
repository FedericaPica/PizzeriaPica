<%@ page import="model.beans.Message" %>
<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <link rel="stylesheet" href="css/validator.css">
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

<c:set var = "mess"  value = "${message.type}"/>
<c:if test = "${mess == 'SUCCESS'}">
    <script>
    $(document).ready(function() {
        $(".swal2-confirm").click(function() {
            window.close();
        });
    });
    Swal.fire({
        icon: '${fn:toLowerCase(mess)}',
        title: '${message.title}',
        text: '${message.body}'
    })
    window.onunload = function(){
        window.opener.location.reload();
    };
    </script>
</c:if>

<c:set var = "flag" value = "${flag}"/>
    <c:choose>
        <c:when test="${flag == 'insert'}">
            <form  onsubmit="return validateInsertCategoria()" action="InserisciCategoriaDBServlet" method="post" class="form">
        </c:when>

        <c:when test="${flag == 'update'}">
            <form action="ModificaCategoriaDBServlet" method="post">
        </c:when>
    </c:choose>

            <table>
                <tr class="form-field">

                    <c:choose>
                        <c:when test="${flag == 'insert'}">

                            <td><label for="nomeIdInsert"> Nome: </label></td>
                            <td><input type="text" id="nomeIdInsert" name="nome" autocomplete="off"></td>
                                <small></small>

                        </c:when>

                        <c:when test="${flag == 'update'}">
                            <td><label for="nomeIdUpdate"> Nome: </label></td>
                            <td><input type="text" id="nomeIdUpdate" name="nome" value="${categoria.nome}"></td>

                            </c:when>
                    </c:choose>

                </tr>
                <tr class="form-field">
                    <c:choose>
                        <c:when test="${flag == 'insert'}">

                            <td><label for="priorityIdInsert"> Priorit&agrave;: </label></td>
                            <td><input type="number" id="priorityIdInsert" name="priority" autocomplete="off"></td>
                            <small></small>
                        
                        </c:when>

                        <c:when test="${flag == 'update'}">
                            <td><label for="priorityIdUpdate"> Priorit&agrave;: </label></td>
                            <td><input type="number" id="priorityIdUpdate" name="priority" value="${categoria.priority}"></td>
                        </c:when>
                    </c:choose>

                </tr>
                <tr>
                    <div class="form-field">
                    <td style="width: 100%; border:none; text-align: right"><input type="submit" value="Salva"></td>
                    </div>
                    <c:if test="${flag == 'update'}">
                        <input type="hidden" name="id" value="${categoria.id}">
                    </c:if>
                </tr>
            </table>
        </form>

<script src="js/validator.js"></script>
</body>
</html>