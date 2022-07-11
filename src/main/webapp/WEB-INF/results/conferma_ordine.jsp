<%@ taglib prefix ='c' uri ='http://java.sun.com/jsp/jstl/core' %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/functions'  prefix="fn"%>

<!DOCTYPE html>

<html>
<head>
    <%@include file="/includes.html"%>
    <%@ page import="model.beans.Prodotto" %>
    <%@ page import="model.beans.Utente" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="model.beans.Categoria" %>
    <%@ page import="java.text.DecimalFormat" %>
    <%@ page import="model.beans.Message" %>
    <title> Home </title>
    <style>
       #confirm {
            background: rgba(0, 0, 0, 0.7);
            text-align: left;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.3), 0 6px 20px 0 rgba(0, 0, 0, 0.29);
            position: relative;
            left: 150px;
            max-width: 700px;
        }
       table {
           text-align: center;
           color: white;
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
    </style>
</head>
<body>
<%@include file="/header.jsp"%>
<%@include file="/nav.jsp"%>

<div id="confirm" class="col-t-9 col-6">
    <form action="ConfermaOrdineServlet" method="post">
  <table>

        <tr>
              <td>
                  <label for="dataRitiro">Scegli una data:</label>
                  <input type="date" id="dataRitiro" name="dataRitiro">
              </td>

              <td>
                  <label for="oraRitiro">Scegli un orario:</label>
                  <select name="oraRitiro" id="oraRitiro">
                    <c:forEach items="${orariDisponibili}" var="ora">
                      <option value="${ora.orario}">${ora.orario}</option>
                    </c:forEach>
                  </select>
              </td>
        </tr>

        <tr>
            <td> Riepilogo</td>
            <td>
                <table>
                    <tr>
                        <th>Q.t&agrave;</th>
                        <th>Prodotto</th>
                        <th>Prezzo</th>
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
                            ${prod.prezzo - ((prod.prezzo*prod.sconto)/100)}
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
                        <td>Totale: ${totale}</td>
                    </tr>
                </table>
            </td>
        </tr>
  </table>
  <input type="submit" value="Conferma">
  <input type="hidden" id="carrelloId" name="carrelloId" value="${carrelloId}">
  <input type="hidden" id="totale" name="totale" value="${totale}">
</form>
</div>
</body>
</html>
