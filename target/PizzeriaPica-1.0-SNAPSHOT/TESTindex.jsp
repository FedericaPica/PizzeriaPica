<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>

<html>
<head>
    <%@include file="includes.html"%>
    <%@ page import="model.beans.Prodotto" %>
    <%@ page import="model.beans.Utente" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="model.beans.Categoria" %>
    <%@ page import="java.text.DecimalFormat" %>
    <%@ page import="model.beans.Message" %>
    <title> Home </title>
    <style>
        .swal2-popup {
            background-color: #A9BAC1;
        }
        .swal2-styled.swal2-confirm {
            background-color: #2B822F;
        }
        .swal2-styled.swal2-cancel {
            background-color: #DC3741;
        }
        .swal2-icon.swal2-success .swal2-success-ring {
            border: 0.25em solid #2B822F;
        }
    </style>
</head>

<body>
<%@include file="header.jsp"%>
<script>



    function addCart(idProdotto) {
        Swal.fire({
            title: "Aggiungere all'ordine?",
            showCancelButton: true,
            confirmButtonText: 'S&igrave;',
            cancelButtonText: 'No',
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                Swal.fire('Aggiunto!', '', 'success')
                $.getJSON("AggiungiAlCarrelloServlet", {idProdotto: idProdotto}, function (data) {
                    <% Utente utente = (Utente)session.getAttribute("utente");
                    if (utente == null) { %>
                    $("#prodottiCarrelloSession").append("<li>" + data.nome + data.prezzo + data.sconto + "% </li>");
                    $("#totaleSession").html("Totale:" + data.totale);
                    <% } else { %>
                    mostraCarrelloAside();
                    <% } %>
                });

            }
        })
    }

    function mostraListe(idCategoria) {
        var id = idCategoria;
        $.getJSON("ListaProdottiServlet", {idCategoria: idCategoria}, function(data){
            if (data == null) {
                $("#li" + id).append("<p> Nessun prodotto presente. </p>");
            } else {
                for (let i = 0; i < data.length; i++) {
                    $("#li" + id).append('<p class="prodotto" onclick="addCart(' + data[i].id + ')">' +
                        '<span style="font-size: 25px; color:white;">' + data[i].nome + '</span>' +
                        '<b> ' + (Math.round(data[i].prezzo * 100) / 100).toFixed(2) + ' &euro; </b>' +
                        ' <br>' + data[i].descrizione + '</p>');

                }
            }
        });
    }
</script>

<a href="Temporanea"> admin </a>

<div style="position:sticky; float:left; max-width: 20%">
    <%@include file="nav.jsp"%>
</div>
<div style="float:left; max-width: 50%">
    <section id="section">
        <ul class="tilesWrap">
            <% ArrayList<Categoria> categorie = (ArrayList<Categoria>) application.getAttribute("categorie");
                for (Categoria c : categorie) {%>
            <li id="li<%=c.getId()%>">
                <img src="images/pizza.jpg"  onload="mostraListe(<%=c.getId()%>)"></img>
                <h3 id="name<%=c.getNome()%>" ><%=c.getNome()%></h3>


            </li>
            <% } %>
        </ul>
    </section>
</div>
<div style="float:left; max-width: 30%">
    <%@include file="aside.jsp"%>
</div>

</body>
</html>