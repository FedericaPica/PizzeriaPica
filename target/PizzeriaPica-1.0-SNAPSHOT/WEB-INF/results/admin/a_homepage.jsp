<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
<head>
    <%@ page import="model.beans.Message" %>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <%@ page import="model.beans.Prodotto" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="model.beans.Categoria" %>
    <script type="text/javascript" src="js/admin.js"></script>


    <title> Homepage admin </title>
</head>

<body>
    <%@include file="../../../header.jsp"%>

    <div class="left elencoAdmin" style="width:40%; float:left;" >
        <div>
            <h3  id="categorieA"> Categorie</h3>
            <button id="inserisciCategoria"> Inserisci nuova categoria </button>

            <h3  id="utentiA"> Utenti</h3>

            <h3 id="ordiniA"> Ordini </h3>

            <h3> Festivi</h3>

            <h3 id="orariA"> Orari</h3>
            <button id="inserisciOrario"> Inserisci nuovo slot orario </button>

            <h3> <a href="LogoutServlet"> Logout </a></h3>
        </div>
    </div>

    <div class="elenco2" class="center" style="width:50%; float:left;">
        <table id="mostra">


        </table>
    </div>

</body>
</html>
