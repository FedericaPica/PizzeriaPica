<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html>
<head>
    <%@ page import="model.beans.Message" %>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <link rel="stylesheet" href="css/responsive_admin.css">
    <%@ page import="model.beans.Prodotto" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="model.beans.Categoria" %>
    <script type="text/javascript" src="js/admin.js"></script>


    <title> Homepage admin </title>
</head>

<body>
    <%@include file="../../../header.jsp"%>

    <div class="left elencoAdmin" >
        <div id="adminMenu">
            <h3  id="categorieA"> Categorie</h3>
            <button id="inserisciCategoria"> Inserisci nuova categoria </button>

            <h3  id="utentiA"> Utenti</h3>

            <h3 id="ordiniA"> Ordini </h3>

            <h3 id="festiviA"> Festivi</h3>
            <button id="inserisciFestivo"> Inserisci nuovo giorno di chiusura </button>

            <h3 id="orariA"> Orari</h3>
            <button id="inserisciOrario"> Inserisci nuovo slot orario </button>

            <h3> <a href="LogoutServlet"> Logout </a></h3>
        </div>

            <button id="burgerAdmin">O</button>

    </div>

    <div class="elenco2 center" >
        <table id="mostra" style="max-width: 100%;">


        </table>
    </div>

</body>
</html>
