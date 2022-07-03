<%@ page import="model.beans.Message" %>
<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <title>Inserisci orario</title>
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


<form action="InserisciOrarioDBServlet" method="post">
        <table>
            <tr>
                 <td><label for="orarioId"> Orario: </label></td>
                <td><input type="time" id="orarioId" name="orario"></td>

            </tr>
            <tr>
                <td style="width: 100%; border:none; text-align: right"><input type="submit" value="Salva"></td>
            </tr>
        </table>
    </form>

</body>
</html>