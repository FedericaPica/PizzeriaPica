<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../../includes.html"%>
    <title> Chi siamo </title>
    <style>
        #chisiamo {
            background: rgba(0, 0, 0, 0.7);
            text-align: left;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
            position: relative;
            left: 150px;
            max-width: 700px;
        }
        #b {
            color : #F18F01;
            font-size: 20px;
            text-align: center;
            margin: 5px;
        }
        .foto {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.5), 0 6px 20px 0 rgba(0, 0, 0, 0.39);
            height: 250px;
            width: 300px;
            position:relative;
        }
        #maps {
            color : #F18F01;
        }
        #chisiamo > p, ol{
            padding: 5px;
            line-height: 18px;
            color: #A9BAC1;
        }


    </style>

</head>
<body>
<%@include file="../../header.jsp"%>

<%@include file="../../nav.jsp"%>

<div id="chisiamo" class="col-t-9 col-6">
    <b id="b"> Un punto di riferimento nella città di Benevento, da oltre quarant'anni.</b>
    <img class="foto" src="images/famiglia.jpeg" style="float: right; right: -5%;
            margin-top: 3%;">
    <p> Siamo lieti di darvi il benvenuto alla pizzeria rosticceria Pica! </p>
    <p> La nostra storia inizia ad opera dei titolari Marcello e Carmela che, per amore,
        hanno deciso di unire le forze e investire in una piccola attività a conduzione
        familiare. <br>
        Il locale si afferma sin da allora grazie alla qualità offerta, alla cortesia del personalee all'attenzione per il cliente.<br>
        Si trova in centro città, a pochi passi dal duomo e da altri siti storici, tra cui l'anfiteatro romano e
        l'arco di Traiano, precisamente in
        <a id="maps" href="https://www.google.it/maps/place/Pizzeria+Pica/@41.1327035,14.7717021,17z/data=!3m1!4b1!4m5!3m4!1s0x133a3a1296044f37:0xac007ce43c8b25a0!8m2!3d41.1326997!4d14.7738754" target="_blank" title="Apri in Google maps!"> Corso Vittorio Emanuele III, 25, 82100 Benevento.</a>
        <br> L'attenzione alla
        qualità degli ingredienti e l'utilizzo di prodotti di prima qualità ci rendono orgogliosi e molto apprezzati
        dalla nostra affezionata clientela. Inoltre, l'ottimo rapporto qualità/prezzo è ciò che ci ha sempre
        contraddistinto.<br> Nel corso degli anni, il nostro filo conduttore è stato la salvaguardia della tradizione, sia nei
        prodotti offerti che nei metodi di preparazione.</p>
    <ol style="list-style-type: none; text-align: center">
        <li style="color: #F18F01"> <b> ORARI</b></li>
        <li> lun/ven 10:00-15:00 18:00-22:00</li>
        <li> sab 18:00-22:00</li>
        <li> domenica chiuso</li>
    </ol>
    <img class="foto" src="images/papy.jpeg" style="float: left; left: -5%; width: 400px">
    <p style="color: #F18F01"> <b>INGREDIENTI </b></p>
    <p> <b>Impasto per pizze, calzoni, involtini e focacce: </b> farina di frumento tipo "00", acqua,
    lievito di birra, sale.</p>
    <p> <b>Farcitura per pizze, calzoni e involtini: </b> pomodori pelati, mozzarella,
    funghi, origano, aglio, uovo, spalla cotta, melenzane, patate, spinaci, rucola, grana,
    prosciutto crudo, emmental, fontina, gorgonzola, olio di oliva, sale, pepe.</p>
    <p> <b>Farcitura per panini e focacce: </b>  rucola, parmigiano, petto di pollo,
    insalata, pomodoro, tonno,mozzarella, carciofini, speck, uovo, melanzane, formaggio velè,
    prosciutto crudo, spalla cotta, wurstel, patate, sale, olio di oliva, sale, pepe.</p>
    <p><b>Pancarrè per tramezzini:</b> farina di frumento tipo "00", acqua, strutto di puro
    suino, lievito di birra, destrosio, sale, propinato di calcio.</p>
    <p> <b>Farcitura per tramezzini: </b> spalla cotta, carotine, spinaci, lattuga, maionese,
    mozzarella, prosciutto crudo, pomodoro, tonno, uovo sodo, wurstel, emmental.</p>
    <p> Le fritture vengono fritte con olio di semi di soia.</p>
</div>

<%@include file="../../footer.jsp"%>
</body>
</html>
