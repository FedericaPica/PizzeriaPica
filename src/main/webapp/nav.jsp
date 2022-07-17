
<nav id="navBar" style="height: 100%">
    <ul>
        <c:set var = "utente" value = "${utente}"/>

        <c:if test = "${utente != null}">
            <li style="background-color:black; color: #A9BAC1;"> Ciao <b> ${utente.nome}</b> </li>
        </c:if>

        <li> <a href="ChiSiamoServlet"> Chi siamo </a></li>

        <li id="menu">Menu prodotti </li>
        <ul id="categorie"> </ul>

        <c:choose>
            <c:when test="${utente == null}">
                <li> <a href="AuthServlet"> Accedi/Registrati </a> </li>
            </c:when>

            <c:when test="${utente != null}">
                <li> <a href="MieiOrdiniServlet"> I miei ordini</a></li>
                <li> <a href="LogoutServlet"> Logout </a> </li>
            </c:when>
        </c:choose>

        <li> <a href="index.jsp"> Home </a></li>

    </ul>
</nav>
