<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>

<html>
<head>
   <%@include file="includes.html"%>
    <%@ page import="model.beans.Prodotto" %>
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

    <a href="Temporanea"> admin </a>
<div class="row">

    <%@include file="nav.jsp"%>


    <section class="col-t-6 col-6" >
        <ul class="tilesWrap">
            <% ArrayList<Categoria> categorie = (ArrayList<Categoria>) application.getAttribute("categorie");
            for (Categoria c : categorie) {%>
                <li>
                    <img src="images/pizza.jpg"></img>
                    <h3 id="<%=c.getNome()%>"><%=c.getNome()%></h3>
                    <% ArrayList<Prodotto> prodotti = (ArrayList<Prodotto>) application.getAttribute("lista" + c.getNome());
                    if(prodotti != null) {
                    for (Prodotto p : prodotti) {%>
                    <p class="prodotto" onclick="addCart()">
                        <span style="font-size: 25px"><%=p.getNome()%> </span>
                        <b><%=new DecimalFormat("#,##0.00").format(p.getPrezzo())%> &euro; </b>
                        <br> <%=p.getDescrizione()%>
                    </p>
                    <% }} else { %>
                    <p> Nessun prodotto presente. </p>
                    <% } %>

                </li>
            <% } %>
        </ul>
    </section>


    <%@include file="aside.jsp"%>>


</div>
<%@include file="footer.jsp"%>

<script>
    function addCart() {
        Swal.fire({
            title: "Aggiungere all'ordine?",
            showCancelButton: true,
            confirmButtonText: 'S&igrave;',
            cancelButtonText: 'No',
        }).then((result) => {
            /* Read more about isConfirmed, isDenied below */
            if (result.isConfirmed) {
                Swal.fire('Aggiunto!', '', 'success')
            }
            //else if (result.isDenied) {
            //  Swal.fire('Changes are not saved', '', 'info')
            //}
        })
    }
</script>
</body>
</html>