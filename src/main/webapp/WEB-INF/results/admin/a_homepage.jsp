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

    <div class="col-t-4 col-4 elencoAdmin" >
        <div>
            <h3  id="categorieA"> Categorie</h3>
            <button id="inserisciCategoria"> Inserisci nuova categoria </button>

            <h3> Utenti</h3>
            <h3> Ordini </h3>
            <h3> Festivi</h3>
            <h3>Orari</h3>
            <h3> Logout</h3>
        </div>
    </div>

    <div class="elenco2" class="col-8 col-t-8">
        <table id="mostra">


        </table>
    </div>

</body>
</html>
