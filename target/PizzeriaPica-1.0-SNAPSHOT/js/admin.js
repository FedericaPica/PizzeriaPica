
function myPopup(myURL, title, myWidth, myHeight) {
    var left = (screen.width - myWidth) / 2;
    var top = (screen.height - myHeight) / 4;
    var myWindow = window.open(myURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + myWidth + ', height=' + myHeight + ', top=' + top + ', left=' + left);
}

$(document).ready(function() {

    //Categorie:

    $(".categorieA").on("click", function () {
        $("#mostra").empty()
        $.getJSON("MostraCategorieServlet", function (data) {
            $("#mostra").append("<tr> <th>Categoria</th> <th>Priorit&agrave;</th> <th> </th></tr>");
            for (let i = 0; i < data.length; i++) {

                $("#mostra").append("<tr id='categoria" + data[i].id + "'> <td>" + data[i].nome + "</td> <td>" + data[i].priority + "</td>" +

                    "<td style='max-width: 190px'> <button id='deleteCategoria" + data[i].id + "'> Elimina </button>" +
                    "<button id='modificaCategoria" + data[i].id + "'> Modifica </button>" +
                    "<button id='visualizzaCategoria" + data[i].id + "'> Mostra prodotti </button>" +
                    "<button id='inserisciProdottoCat" + data[i].id + "'> Inserisci prodotto </button> </td> </tr>");

                $('#deleteCategoria' + data[i].id).click(function () {
                    let id = data[i].id;
                    $.get('EliminaCategoriaServlet', {"categoriaId": id}, function(data) {
                        items = JSON.parse(data);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                        $('#categoria' + id).remove();
                    });
                });


                $('#modificaCategoria' + data[i].id).click(function () {
                    myPopup("ModificaCategoriaServlet?id=" + data[i].id, "Modifica", 600, 400);
                });

                $('#visualizzaCategoria' + data[i].id).click(function () {
                    myPopup("MostraProdottiServlet?categoria=" + data[i].id, "Prodotti", 800, 400);
                });

                $('#inserisciProdottoCat' + data[i].id).click(function () {
                    myPopup("InserisciProdottoServlet?idCat=" + data[i].id, "Inserisci", 600, 400);
                });
            }
        });
    });

    $(".inserisciCategoria").click(function () {
        myPopup("InserisciCategoriaServlet", "Inserisci", 600, 400);
    })

    // Utenti


    $(".utentiA").on("click", function () {
        $("#mostra").empty()
        $.getJSON("MostraUtentiServlet", function (data) {
            $("#mostra").append("<tr> <th>Nome</th> <th>Cognome</th> <th>Email</th> <th>Telefono</th> <th> </th></tr>");
            for (let i = 0; i < data.length; i++) {

                $("#mostra").append("<tr id='utente" + data[i].id + "'> <td>" + data[i].nome + "</td> <td>" + data[i].cognome + "</td> <td>" + data[i].email + "</td> <td>" + data[i].telefono + "</td>" +

                    "<td style='max-width: 190px'> <button id='deleteUtente" + data[i].id + "'> Elimina </button></td> </tr>");

                $('#deleteUtente' + data[i].id).click(function () {
                    let idUtente= data[i].id;
                    $.get('EliminaUtenteServlet', {"utenteId": data[i].id}, function (data) {
                        items = JSON.parse(data);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                    });
                    $('#deleteUtente' + idUtente).closest("tr").remove();
                });


            }
        });
    });

    //Ordini

    $(".ordiniA").on("click", function () {
        $("#mostra").empty()
        $.getJSON("MostraOrdiniServlet", function (data) {
            $("#mostra").append("<tr> <th>Id</th> <th>Ritiro</th> <th>Totale</th> <th>Cliente</th> <th>Stato</th> <th>Prodotti</th> <th style='width:220px;'> </th></tr>");
            for (let i = 0; i < data.length; i++) {
                let concat_button;
                if(data[i].stato == "ATTIVO") {
                    concat_button = "<td style='padding:0px;'> <button style='width=110px; margin-right: 0px;' id='annullaOrdine" + data[i].id + "'> Annulla ordine </button> <button style='width:110px; margin-left:0px;' id='concludiOrdine" + data[i].id + "'> Concludi ordine </button> </td>";
                } else {
                    concat_button = "<td></td>";
                }
                $("#mostra").append("<tr> <td>" + data[i].id + "</td> <td>" + data[i].ritiroDt + "</td> <td>" + data[i].totale + "</td> <td>" + data[i].utenteid + "</td><td id='stato" + data[i].id + "'>" + (data[i].stato).toLowerCase() + "</td> <td><button style='width:110px;' id='mostraProdottiOrdine" + data[i].id + "'> Mostra prodotti </button></td>" +
                          concat_button + "</tr>");

                $('#annullaOrdine' + data[i].id).click(function () {
                    let idOrdine= data[i].id;
                    $.get('AnnullaOrdineServlet', {"idOrdine": idOrdine}, function (jsonData) {
                        items = JSON.parse(jsonData);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                        if(items.type == "SUCCESS") {
                            $('#stato' + idOrdine).html("annullato");
                            $('#annullaOrdine' + data[i].id).hide();
                            $('#concludiOrdine' + data[i].id).hide();
                        }
                    });
                });

                $('#concludiOrdine' + data[i].id).click(function () {
                    let idOrdine= data[i].id;
                    $.get('ConcludiOrdineServlet', {"idOrdine": idOrdine}, function (jsonData) {
                        items = JSON.parse(jsonData);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                        if(items.type == "SUCCESS") {
                            $('#stato' + idOrdine).html("eseguito");
                            $('#concludiOrdine' + data[i].id).hide();
                            $('#annullaOrdine' + data[i].id).hide();
                        }
                    });
                });

                $('#mostraProdottiOrdine' + data[i].id).click(function () {
                    myPopup("MostraProdottiOrdineServlet?ordine=" + data[i].id, "Prodotti", 800, 400);
                });
            }
        });
    });

    //Festivi
    $(".festiviA").on("click", function () {
        $("#mostra").empty()
        $.getJSON("MostraFestiviServlet", function (data) {
            $("#mostra").append("<tr> <th>Giorni di chiusura</th><th> </th></tr>");
            for (let i = 0; i < data.length; i++) {

                $("#mostra").append("<tr id='festivo" + data[i].id + "'> <td>" + data[i].giorno + "</td>" +

                    "<td style='max-width: 190px'> <button id='deleteFestivo" + data[i].id + "'> Elimina </button></td> </tr>");

                $('#deleteFestivo' + data[i].id).click(function () {
                    let idFestivo= data[i].id;
                    $.get('EliminaFestivoServlet', {"festivoId": data[i].id}, function (data) {
                        items = JSON.parse(data);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                    });
                    $('#deleteFestivo' + idFestivo).closest("tr").remove();
                });


            }
        });
    });

    $(".inserisciFestivo").click(function () {
        myPopup("InserisciFestivoServlet", "Inserisci", 600, 400);
    })


    //Orari

    $(".orariA").on("click", function () {
        $("#mostra").empty()
        $.getJSON("MostraOrariServlet", function (data) {
            $("#mostra").append("<tr> <th>Slot orari</th><th> </th></tr>");
            for (let i = 0; i < data.length; i++) {

                $("#mostra").append("<tr id='orario" + data[i].id + "'> <td>" + data[i].orario + "</td>" +

                    "<td style='max-width: 190px'> <button id='deleteOrario" + data[i].id + "'> Elimina </button></td> </tr>");

                $('#deleteOrario' + data[i].id).click(function () {
                    let idOrario= data[i].id;
                    $.get('EliminaOrarioServlet', {"orarioId": data[i].id}, function (data) {
                        items = JSON.parse(data);
                        Swal.fire({
                            icon: items.type.toLowerCase(),
                            title: items.title,
                            text: items.body
                        });
                    });
                    $('#deleteOrario' + idOrario).closest("tr").remove();
                });


            }
        });
    });

    $(".inserisciOrario").click(function () {
        myPopup("InserisciOrarioServlet", "Inserisci", 600, 400);
    })

})