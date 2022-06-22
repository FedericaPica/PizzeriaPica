<%@ page import="model.beans.Message" %>
<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<form action="InserisciProdottoDBServlet" method="post">
    </c:when>

    <c:when test="${flag == 'update'}">
    <form action="ModificaProdottoDBServlet" method="post">
        </c:when>
        </c:choose>

        <table>
            <tr>

                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <td><label for="nomeIdInsert"> Nome: </label></td>
                        <td><input type="text" id="nomeIdInsert" name="nome"></td>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                        <td><label for="nomeIdUpdate"> Nome: </label></td>
                        <td><input type="text" id="nomeIdUpdate" name="nome" value="${prodotto.nome}"></td>
                    </c:when>
                </c:choose>

            </tr>
            <tr>
                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <td><label for="prezzoIdInsert"> Prezzo: </label></td>
                        <td><input type="text" id="prezzoIdInsert" name="prezzo"></td>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                        <td><label for="prezzoIdUpdate"> Prezzo: </label></td>
                        <td><input type="text" id="prezzoIdUpdate" name="prezzo" value="${prodotto.prezzo}"></td>
                    </c:when>
                </c:choose>

            </tr>
            <tr>
                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <td><label for="descrizioneIdInsert"> Descrizione: </label></td>
                        <td><textarea id="descrizioneIdInsert" name="descrizione" rows="3" cols="30"></textarea></td>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                        <td><label for="descrizioneIdUpdate"> Descrizione: </label></td>
                        <td><textarea id="descrizioneIdUpdate" name="descrizione" rows="3" cols="30">${prodotto.descrizione}</textarea></td>
                    </c:when>
                </c:choose>

            </tr>
            <tr>
                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <td><label for="scontoIdInsert"> Sconto: </label></td>
                        <td><input type="number" id="scontoIdInsert" name="sconto"></td>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                        <td><label for="scontoIdUpdate"> Sconto: </label></td>
                        <td><input type="number" id="scontoIdUpdate" name="sconto" value="${prodotto.sconto}"></td>
                    </c:when>
                </c:choose>

            </tr>
            <tr>
                <td style="width: 100%; border:none; text-align: right"><input type="submit" value="Salva"></td>
                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <input type="hidden" name="idCat" value="${idCategoria}">
                    </c:when>
                    <c:when test="${flag == 'update'}">
                        <input type="hidden" name="idProd" value="${prodotto.id}">
                    </c:when>
                </c:choose>
            </tr>
        </table>
    </form>

</body>
</html>