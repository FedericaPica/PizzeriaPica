<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>

<html>
<head>
    <%@include file="/includes.html"%>
    <link rel="stylesheet" href="css/responsive.css">
    <%@ page import="model.beans.Prodotto" %>
    <%@ page import="model.beans.Utente" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="model.beans.Categoria" %>
    <%@ page import="java.text.DecimalFormat" %>
    <%@ page import="model.beans.Message" %>
    <link rel="stylesheet" href="css/validator.css">
    <title> Conferma ordine </title>
    <style>
       table {
           text-align: center;
           color: white;
           max-width:100%;
       }
       td {
           padding: 7px;
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
           position: relative;
           left: 43%;
       }

       .ui-datepicker-calendar {
           color: black;
       }
    </style>
</head>
<body>
<%@include file="/header.jsp"%>
<div class="left">
<%@include file="/nav.jsp"%>
</div>
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
            text: "${message.body}"
        })
        window.onunload = function(){
            window.opener.location.reload();
        };
    </script>
</c:if>

<div class="center shadows" >
    <form action="ConfermaOrdineServlet" method="post" onsubmit="return validateOrdine()">
  <table id="tabellaOrdine">

        <tr>
              <td>
                  <label for="dataRitiro" style="color:#F18F01">Scegli una data:</label>
                  <input class="form-field" type="text" placeholder="gg/mm/aaaa" id="dataRitiro" name="dataRitiro">
              </td>

              <td>
                  <label for="oraRitiro" style="color:#F18F01">Scegli un orario:</label>
                  <select name="oraRitiro" id="oraRitiro">
<%--                    <c:forEach items="${orariDisponibili}" var="ora">--%>
<%--                      <option value="${ora.orario}">${ora.orario}</option>--%>
<%--                    </c:forEach>--%>
                        <option>Seleziona prima una data</option>
                  </select>
              </td>
        </tr>

        <tr>
            <td style="color:#F18F01"> Riepilogo</td>
            <td>
                <table>
                    <tr>
                        <th>Q.t&agrave;</th>
                        <th>Prodotto</th>
                        <th>Prezzo scontato</th>
                        <th>Sconto</th>
                    </tr>

                    <c:forEach items="${prodottiCarrelloDb}" var="prod">
                    <tr>
                        <td>
                           ${prod.quantita}
                        </td>
                        <td>
                           ${prod.nome}
                        </td>
                        <td>
                            ${prod.prezzo}
                        </td>
                         <td>
                             <c:set var = "sconto"  value = "${prod.sconto}"/>
                             <c:if test = "${sconto != 0}">
                                 ${prod.sconto}%
                             </c:if>
                         </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td style="color:#F18F01"> <b>Totale: ${totale} &euro; </b></td>
                    </tr>
                </table>
            </td>
        </tr>
  </table>
  <input id="inviaForm" type="submit" value="Conferma">
  <input type="hidden" id="carrelloId" name="carrelloId" value="${carrelloId2}">
  <input type="hidden" id="totale" name="totale" value="${totale}">
</form>
</div>
<script>

    $(document).ready(function() {
        var disabledDates = [];
        var minData = new Date();
        var month = String(minData.getMonth() + 1).padStart(2, '0');
        var year = minData.getFullYear();
        var day = String(minData.getDate()+1).padStart(2, '0');
        $.getJSON("PrelevaFestiviServlet", function(data) {
            for(let i=0; i < data.length; i++) {

                dateString = (data[i].giorno).replace('gen', 'jan').replace('mag', 'may').replace('giu', 'jun').replace('lug', 'jul').replace('ago', 'aug').replace('set', 'sep').replace('dic','dec');
                dt = formatDate(dateString);
                disabledDates.push(dt);
            }
        });
        $('#dataRitiro').datepicker({
            dateFormat: 'yy-mm-dd',
            beforeShowDay: function(date){
                var day = date.getDay();
                var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
                console.log(string);
                return [ day == 0 ? false : disabledDates.indexOf(string) == -1 ]
            },
            minDate: new Date(year + "-" + month + "-" + day),
            onSelect: function(date, instance) {
                $.getJSON("PrelevaOrariDisponibiliServlet", {"ritiroDt": date }, function(data) {
                    if (data.length == 0) {
                        Swal.fire({
                            icon: 'error',
                            title: 'Errore',
                            text: "Non ci sono orari disponibili per questa data."
                        });
                        $("#inviaForm").attr('disabled', 'disabled');
                    }
                    $("#oraRitiro").html("");
                    for(let i=0; i < data.length; i++) {
                        //data[i].orario = data[i].orario.replace(' AM', '').replace(' PM', '');
                        var options = "";
                        $("#oraRitiro").append("<option value='" + data[i] + "'>" + data[i] + "</option>");
                    }

                    $("#inviaForm").removeAttr('disabled');
                });
            }
        });

    });


    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [year, month, day].join('-');
    }
</script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="Stylesheet" type="text/css" />
</body>
</html>
