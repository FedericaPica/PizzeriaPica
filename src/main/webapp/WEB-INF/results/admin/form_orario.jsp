<%@ page import="model.beans.Message" %>
<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <link rel="stylesheet" href="css/validator.css">
    <link rel="stylesheet" href="css/responsive_admin.css">
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
<c:if test = "${mess != null}">
    <script>
        $(document).ready(function() {
            $(".swal2-confirm").click(function() {
                window.close();
            });
        });
        Swal.fire({
            icon: '${fn:toLowerCase(mess)}',
            title: '${message.title}',
            text: "${message.body}"
        })
        window.onunload = function(){
            window.opener.location.reload();
        };
    </script>
</c:if>
<div class="container">
<form class="form" action="InserisciOrarioDBServlet" method="post" onsubmit="return validateOrario()">

                <div class="form-field">
                 <label for="orarioId"> Orario: </label>
                <input type="time" id="orarioId" name="orario" autocomplete="off">
                    <small></small>
                </div>



                <div class="form-field">
               <input type="submit" value="Salva">
                </div>

    </form>
</div>
<script src="js/validator.js" ></script>
</body>
</html>