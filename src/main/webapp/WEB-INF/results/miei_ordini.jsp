<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../includes.html"%>
    <title> I miei ordini </title>
    <style>
        #mieiOrdini {
            background: rgba(0, 0, 0, 0.7);
            text-align: left;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
            position: relative;
            left: 150px;
            max-width: 700px;
        }
    </style>
</head>
<body>
<%@include file="../../header.jsp"%>
<table>
    <tr>
        <td>
            <%@include file="../../nav.jsp"%>
        </td>
        <td>
                <table>
                    <tr>
                        <th>Ritiro</th>
                        <th>Totale</th>
                        <th>Stato</th>
                    </tr>
                    <tr>
                        <td>${o.ritiroDt}</td>
                        <td>${o.totale}</td>
                        <td>${o.stato}</td>
                    </tr>
                </table>
                <table>
                    <tr>Prodotti</tr>
                    <tr>

                    </tr>
                </table>
        </td>
    </tr>
</table>
</body>
</html>
