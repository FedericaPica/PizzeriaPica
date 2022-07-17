<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside>
    <ul><li><div style="left:0px; margin:0px; color:white;" class="shadows">

        <table id="carrello-table" style="width:100%; text-align:center;">
            <c:if test="${utente == null}">
                <script>mostraCarrelloTableAnonimo();</script>
            </c:if>
        <c:if test= "${utente != null}">

            <script> mostraCarrelloTableUtente();</script>

        </c:if>
        </table>
    </div>
        <li id="totale" style="display: none;"> </li>

        <li id="order" style="display: none;"> <a href="ProcediOrdineServlet"> Procedi all'ordine </a></li>
    </ul>


</aside>