<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside>

    <ul>
        <li> <div style="left:0px; margin:0px; color:white;" class="shadows">
        <table>
            <tr>
                <th></th>
                <th>Prezzo</th>
                <th>Sconto</th>
            </tr>
        </table>
        </div></li>
        <c:set var = "utente"  value = "${utente}"/>
        <c:if test= "${utente == null}">

            <ul id="prodottiCarrelloSession">
                <c:forEach items="${prodottiCarrello}" var="prodotto">
                    <li> ${prodotto.nome} ${prodotto.prezzo} ${prodotto.sconto}</li>
                </c:forEach>
            </ul>

        </c:if>

        <c:if test= "${utente != null}">
            <ul id="prodottiCarrelloDb">

            </ul>

            <script>
                mostraCarrelloAside();
            </script>

        </c:if>

        <li id="totaleSession">
        </li>

        <li id="order"> <a href="ProcediOrdineServlet"> Procedi all'ordine </a></li>
    </ul>


</aside>