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
        <th>Quantità</th>
        <th>Prodotto</th>
        <th>Prezzo d'acquisto</th>
    </tr>
    <c:forEach items="${prodottiOrdine}" var="prodotto">
        <tr>
            <td> ${prodotto.quantita}</td>
            <td> ${prodotto.nomeProdotto} &euro;</td>
            <td> ${prodotto.prezzoAcquisto}</td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
