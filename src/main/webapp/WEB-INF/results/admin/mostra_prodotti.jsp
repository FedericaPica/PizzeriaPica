<%@ page import="model.beans.Message" %>
<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <title>Prodotti</title>
    <style>
        table {
            max-width: 800px;
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
<script>
    $(document).ready(function() {
        <c:if test = "${mess != null}">
        Swal.fire({
            icon: '${fn:toLowerCase(mess)}',
            title: '${message.title}',
            text: '${message.body}'
        });
        </c:if>
        <c:forEach items="${prodotti}" var="prodotto">
                $("#modifica" + ${prodotto.id}).click(function(){
                    window.location.href="ModificaProdottoServlet?idProdotto="+${prodotto.id};
                });

                $("#elimina" + ${prodotto.id}).on("click", function() {
                    $.get('EliminaProdottoServlet', {"idProdotto": ${prodotto.id}}, function(data) {
                        items = JSON.parse(data);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                        $("#elimina" + ${prodotto.id}).closest("tr").remove();
                    });

                });
        </c:forEach>
        });
    </script>



    <table>
        <tr>
            <th>Nome</th>
            <th>Prezzo</th>
            <th>Descrizione</th>
            <th>Sconto</th>
            <th  style='width: 150px'></th>
        </tr>
        <c:forEach items="${prodotti}" var="prodotto">
            <tr>
            <td> ${prodotto.nome}</td>
            <td> ${prodotto.prezzo} &euro;</td>
            <td> ${prodotto.descrizione}</td>
            <td> ${prodotto.sconto} %</td>
            <input type="hidden" name="idCat" value="${idCategoria}">
            <td>
                <button id='modifica${prodotto.id}'> Modifica </button>
                <button id='elimina${prodotto.id}'> Elimina </button> </td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
