<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<aside class="col-t-4 col-4">

    <p> Carrello </p>
    <ul>
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
            Totale: ${totale}
        </li>
        <li id="order"> Procedi all'ordine</li>
    </ul>


</aside>