<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../includes.html"%>
    <link rel="stylesheet" href="css/validator.css">
    <title>Accedi o Registrati</title>
        <style>
            #accedi, #registrati {
                background: rgba(0, 0, 0, 0.7);
                text-align: center;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
                position: relative;
                max-width: 500px;
                margin: 30px;
                padding: 20px;
                color: #A9BAC1;
                font-size: 18px;
            }
            input[type=submit] {
                background-color: #F18F01;
                color: white;
                border: 2px outset #A9BAC1;
                padding: 5px 10px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                margin: 4px 2px;
                cursor: pointer;
                font-size: 15px;
            }
            input[type=text], input[type=password] {
                background-color: #A9BAC1;
                color: black;
                padding: 5px 10px;
            }
        </style>
</head>
<body>
<%@include file="../../header.jsp"%>
<%@include file="../../nav.jsp"%>
<div id="accedi" class="col-4 col-t-4 container">

    <form id="formAccedi" class="form">
        <div class="form-field">
            <label for="emailIdAccedi"> <b> Indirizzo di posta elettronica: </b> </label>
            <input type="text" id="emailIdAccedi" name="emailAccedi" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="passwordIdAccedi"><b>  Password: </b> </label>
            <input type="password" id="passwordIdAccedi" name="passwordIdAccedi" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <input type="submit" value="Registrati">
        </div>
    </form>
</div>


<div id="registrati" class="col-5 col-t-5 container">
    <form id="formRegistrati" class="form"  >   <!-- action="/RegistrazioneServlet" method="post" -->
        <div class="form-field">
            <label for="nomeId"> <b> Nome: </b> </label>
            <input type="text" id="nomeId" name="nome" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="cognomeId"> <b> Cognome:</b>  </label>
            <input type="text" id="cognomeId" name="cognome" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="emailIdRegistrati"> <b> Indirizzo di posta elettronica: </b> </label>
            <input type="text" id="emailIdRegistrati" name="emailRegistrati" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="passwordIdRegistrati"><b>  Password: </b> </label>
            <input type="password" id="passwordIdRegistrati" name="passwordIdRegistrati" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="passwordConferma"> <b> Conferma password:</b> </label>
            <input type="password" id="passwordConferma" name="passwordConferma" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="telefonoId"> <b> Telefono:</b>  </label>
             <input type="text" id="telefonoId" name="telefono" autocomplete="off" value="+(39) ...">
            <small></small>
        </div>

        <div class="form-field">
        <input type="submit" value="Registrati">
        </div>

    </form>
</div>
<script src="../../js/validator.js"></script>
</body>
</html>
