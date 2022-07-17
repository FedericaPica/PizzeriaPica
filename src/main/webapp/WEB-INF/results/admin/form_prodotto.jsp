<%@ page import="model.beans.Message" %>
<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <%@include file="../../../includes.html"%>
    <link rel="stylesheet" href="css/admin.css" type="text/css">
    <link rel="stylesheet" href="css/validator.css">
    <title>Modifica categoria</title>
    <style>
        table {
            max-width: 500px;
            margin: auto;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
        }
        table td, table th {
            border: 1px solid black;
        }
    </style>
</head>
<body>

<c:set var = "mess"  value = "${message.type}"/>
<c:if test = "${mess != null}">
    <script>
        $(document).ready(function() {
            $(".swal2-confirm").click(function() {
                window.close();
            });
        });
        Swal.fire({
            icon: '${fn:toLowerCase(mess)}',
            title: '${message.title}',
            text: '${message.body}'
        })
        window.onunload = function(){
            window.opener.location.reload();
        };
    </script>
</c:if>
<div class="container">
<c:set var = "flag" value = "${flag}"/>
<c:choose>
<c:when test="${flag == 'insert'}">
<form action="InserisciProdottoDBServlet" method="post" onsubmit="return validateInsertProdotto()">
    </c:when>

    <c:when test="${flag == 'update'}">
    <form action="ModificaProdottoDBServlet" method="post" onsubmit="return validateUpdateProdotto()">
        </c:when>
        </c:choose>


                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <div class="form-field">
                        <label for="nomeIdInsert"> Nome: </label>
                        <input type="text" id="nomeIdInsert" name="nome" autocomplete="off">
                            <small></small>
                        </div>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                    <div class="form-field">
                        <label for="nomeIdUpdate"> Nome: </label>
                        <input type="text" id="nomeIdUpdate" name="nome" value="${prodotto.nome}" autocomplete="off">
                        <small></small>
                    </div>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <div class="form-field">
                        <label for="prezzoIdInsert"> Prezzo: </label>
                        <input type="text" id="prezzoIdInsert" name="prezzo" autocomplete="off">
                            <small></small>
                        </div>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                    <div class="form-field">
                       <label for="prezzoIdUpdate"> Prezzo: </label>
                        <input type="text" id="prezzoIdUpdate" name="prezzo" value="${prodotto.prezzo}" autocomplete="off">
                    <small></small>
                    </div>
                        </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <div class="form-field">
                        <label for="descrizioneIdInsert"> Descrizione: </label>
                        <textarea id="descrizioneIdInsert" name="descrizione" rows="3" cols="30"></textarea>
                            <small></small>
                        </div>
                            </c:when>

                    <c:when test="${flag == 'update'}">
            <div class="form-field">
                        <label for="descrizioneIdUpdate"> Descrizione: </label>
                        <textarea id="descrizioneIdUpdate" name="descrizione" rows="3" cols="30">${prodotto.descrizione}</textarea>
                <small></small>
            </div>
                </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <div class="form-field">
                        <label for="scontoIdInsert"> Sconto: </label>
                        <input type="number" id="scontoIdInsert" name="sconto">
                            <small></small>
                        </div>
                    </c:when>

                    <c:when test="${flag == 'update'}">
                        <div class="form-field">
                        <label for="scontoIdUpdate"> Sconto: </label>
                        <input type="number" id="scontoIdUpdate" name="sconto" value="${prodotto.sconto}">
            <small></small>
</div>
                    </c:when>
                </c:choose>

                <div class="form-field">
                <input type="submit" value="Salva">
                </div>
                <c:choose>
                    <c:when test="${flag == 'insert'}">
                        <input type="hidden" name="idCat" value="${idCategoria}">
                    </c:when>
                    <c:when test="${flag == 'update'}">
                        <input type="hidden" name="idProd" value="${prodotto.id}">
                    </c:when>
                </c:choose>

            <c:choose>
            <c:when test="${flag == 'insert'}">
            </form>
                </c:when>

                <c:when test="${flag == 'update'}">
                </form>
                    </c:when>
           </c:choose>

</div>
<script src="js/validator.js" ></script>
</body>
</html>