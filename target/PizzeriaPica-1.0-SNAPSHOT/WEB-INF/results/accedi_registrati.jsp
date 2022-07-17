<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../includes.html"%>
    <link rel="stylesheet" href="css/responsive.css">
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
<div class="left">
<%@include file="../../nav.jsp"%>
</div>

<div class="center">
<div id="accedi" class="container" style="float:left">

    <form id="formAccedi" class="form" onsubmit="return validateLogin()" action="LoginServlet"  method="post" >
        <div class="form-field">
            <label for="emailIdAccedi"> <b> Email: </b> </label>
            <input type="text" id="emailIdAccedi" name="emailAccedi" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="passwordIdAccedi"><b>  Password: </b> </label>
            <input type="password" id="passwordIdAccedi" name="passwordAccedi" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <input type="submit" value="Accedi">
        </div>
    </form>
</div>


<div id="registrati" class="container" style="float:left">
    <form id="formRegistrati" onsubmit="return validateRegistration()" action="RegistrazioneServlet"  method="post"  class="form">
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
            <label for="emailIdRegistrati"> <b> Email: </b> </label>
            <input type="text" id="emailIdRegistrati" name="emailRegistrati" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="passwordIdRegistrati"><b>  Password: </b> </label>
            <input type="password" id="passwordIdRegistrati" name="passwordRegistrati" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="passwordConferma"> <b> Conferma password:</b> </label>
            <input type="password" id="passwordConferma" name="passwordConferma" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
            <label for="telefonoId"> <b> Telefono:</b>  </label>
             <input type="text" id="telefonoId" placeholder="prefisso-numero" name="telefono" autocomplete="off">
            <small></small>
        </div>

        <div class="form-field">
        <input type="submit" value="Registrati">
        </div>

    </form>
</div>
</div>
<script src="js/validator.js"></script>
</body>
</html>
