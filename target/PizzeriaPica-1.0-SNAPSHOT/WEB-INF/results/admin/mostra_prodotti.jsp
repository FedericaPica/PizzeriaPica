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
            <td>
                <button id="modifica' + ${prodotto.id} + '"> Modifica </button>
                <button id="elimina' + ${prodotto.id} + '"> Elimina </button>
            </tr>
        </c:forEach>

    </table>
    <% Message message = (Message) request.getAttribute("message");
        if (message != null) { %>
    <script>
        Swal.fire({
            icon: '<%=message.getType().toLowerCase()%>',
            title: '<%=message.getTitle()%>',
            text: '<%=message.getBody()%>'
        })
    </script>

    <%
        }
    %>
</body>
</html>
