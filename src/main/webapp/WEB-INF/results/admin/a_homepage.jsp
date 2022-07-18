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
    <div class="hamburger-menu">
        <input id="menu__toggle" type="checkbox" />
        <label class="menu__btn" for="menu__toggle">
            <span></span>
        </label>

        <ul class="menu__box">
            <li><a href="#" class="menu__item categorieA">Categorie</a></li>
            <li><a href="#" class="menu__item inserisciCategoria" id="">Inserisci nuova categoria</a></li>
            <li><a href="#" class="menu__item utentiA">Utenti</a></li>
            <li><a href="#" class="menu__item ordiniA">Ordini</a></li>
            <li><a href="#" class="menu__item festiviA">Festivi</a></li>
            <li><a href="#" class="menu__item inserisciFestivo">Inserisci giorno festivo</a></li>
            <li><a href="#" class="menu__item orariA">Inserisci giorno festivo</a></li>
            <li><a href="#" class="menu__item inserisciOrario">Inserisci nuovo slot orario</a></li>
            <li><a href="LogoutServlet" class="menu__item">Logout</a></li>
        </ul>
    </div>
    <div class="left elencoAdmin" >
        <div id="adminMenu">
            <h3  class="categorieA"> Categorie</h3>
            <button class="inserisciCategoria"> Inserisci nuova categoria </button>

            <h3  class="utentiA"> Utenti</h3>

            <h3 class="ordiniA"> Ordini </h3>

            <h3 class="festiviA"> Festivi</h3>
            <button class="inserisciFestivo"> Inserisci nuovo giorno di chiusura </button>

            <h3 class="orariA"> Orari</h3>
            <button class="inserisciOrario"> Inserisci nuovo slot orario </button>

            <h3> <a href="LogoutServlet"> Logout </a></h3>
        </div>

    </div>

    <div class="elenco2 center" >
        <table id="mostra" style="max-width: 100%;">


        </table>
    </div>

</body>
</html>
