$(document).ready(function() {
    $("#elenco2").hide();
    $("#categorieA").click(function () {
        $("#mostra").empty()
        $.getJSON("MostraCategorieServlet", function (data) {
            $("#mostra").append("<tr> <th>Categoria</th> <th>Priorit&agrave;</th> <th> </th></tr>");
            for (let i = 0; i < data.length; i++) {
                $("#mostra").append("<tr> <td>" + data[i].nome + "</td> <td>" + data[i].priority + "</td>" +
                    "<td> <button id=" + data[i].nome + "Delete'> Elimina </button>" +
                    "<button id=" + data[i].nome + "Update'> Modifica </button> </td></tr>")
            }
            $("#elenco2").slideToggle("slow", function () {
            });
        });
    });

    $("#elenco2").hide();
    $("#prodottiA").click(function() {
        $("#mostra").empty()
        $.getJSON("MostraProdottiServlet", function (data) {
            console.log("Ciao!");
            $("#mostra").append("<tr> <th>Nome</th> <th>Prezzo</th> <th>Descrizione </th> <th>Sconto</th> <th>Categoria</th><th> </th></tr>");
            for (let i=0; i<data.length; i++) {
                $("#mostra").append("<tr> <td>" + data[i].nome + "</td> <td>" + data[i].prezzo + "</td>" +
                    "<td>" + data[i].descrizione + "</td> <td>" + data[i].sconto + "</td>" +
                    "<td>" + data[i].categoriaid + "</td>" +
                    "<td> <button id=" + data[i].nome + "Delete'> Elimina </button>" +
                    "<button id=" + data[i].nome + "Update'> Modifica </button> </td></tr>")
            }
            $("#elenco2").slideToggle("slow", function(){
            });
        });
    });

})