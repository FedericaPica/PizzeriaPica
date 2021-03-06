<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../includes.html"%>
    <link rel="stylesheet" href="css/responsive.css">
    <title> I miei ordini </title>
    <style>
        table {
            text-align: center;
            color: white;
            border: 1px solid #F18F01;
            width: 98%;
            margin: 7px;
        }
        td {
            padding: 7px;
        }
    </style>
</head>
<body>

<%@include file="../../header.jsp"%>

<div class="left">
            <%@include file="../../nav.jsp"%>
</div>
<div class="center shadows">
<c:forEach items="${map}" var="ordine">
    <table style="border: 3px solid #F18F01;">

    <tr>
    <th>Id ordine</th>
    <th>Ritiro</th>
    <th>Stato</th>
    </tr>
    <tr>
    <td>${ordine.key.id}</td>
    <td>${ordine.key.ritiroDt}</td>
        <c:set var = "stato"  value = "${ordine.key.stato}"/>
        <c:if test = "${stato == 'ATTIVO'}">
            <td style="color:yellow;">
        </c:if>

        <c:set var = "stato"  value = "${ordine.key.stato}"/>
        <c:if test = "${stato == 'ANNULLATO'}">
            <td style="color:red;">
        </c:if>

        <c:set var = "stato"  value = "${ordine.key.stato}"/>
        <c:if test = "${stato == 'ESEGUITO'}">
            <td style="color:green;">
        </c:if>


            ${ordine.key.stato}</td>
    </tr>

        <tr>
            <td style="color:#F18F01"> Prodotti</td>
            <td colspan="2">

                <table>
                    <tr>
                        <th>Q.t&agrave;</th>
                        <th>Prodotto</th>
                        <th>Prezzo</th>
                    </tr>

                    <c:forEach items="${ordine.value}" var="prodotto" varStatus="loop">
                        <tr>
                            <td>
                                    ${prodotto.quantita}
                            </td>
                            <td>
                                    ${prodotto.nomeProdotto}
                            </td>
                            <td>
                                    ${prodotto.prezzoAcquisto}
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td style="color:#F18F01"> <b>Totale: ${ordine.key.totale} &euro; </b></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</c:forEach>
</div>
</body>
</html>
