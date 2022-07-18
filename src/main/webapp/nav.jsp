
<div class="hamburger-menu">
    <input id="menu__toggle" type="checkbox" />
    <label class="menu__btn" for="menu__toggle">
        <span></span>
    </label>

    <ul class="menu__box">
        <c:set var = "utente" value = "${utente}"/>

        <c:if test = "${utente != null}">
            <li style="background-color:black; color: #A9BAC1;"><span class="menu__item"> Ciao <b> ${utente.nome}</b> </span></li>
        </c:if>

        <li> <a class="menu__item" href="ChiSiamoServlet"> Chi siamo </a></li>

        <li class="menuBurger"><span class="menu__item">Menu prodotti </span></li>
        <ul class="categorieBurger"> </ul>

        <c:choose>
            <c:when test="${utente == null}">
                <li> <a class="menu__item" href="AuthServlet"> Accedi/Registrati </a> </li>
            </c:when>

            <c:when test="${utente != null}">
                <li> <a class="menu__item" href="MieiOrdiniServlet"> I miei ordini</a></li>
                <li> <a class="menu__item" href="LogoutServlet"> Logout </a> </li>
            </c:when>
        </c:choose>

        <li> <a class="menu__item" href="index.jsp"> Home </a></li>
        <li> <a id="carrelloMenu" class="menu__item" href="index.jsp#carrello-table"> Carrello </a></li>

    </ul>
</div>

<nav class="menu" id="navBar">
    <ul>
        <c:set var = "utente" value = "${utente}"/>

        <c:if test = "${utente != null}">
            <li style="background-color:black; color: #A9BAC1;"> Ciao <b> ${utente.nome}</b> </li>
        </c:if>

        <li> <a href="ChiSiamoServlet"> Chi siamo </a></li>

        <li class="menuBurger">Menu prodotti </li>
        <ul class="categorieBurger"> </ul>

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
