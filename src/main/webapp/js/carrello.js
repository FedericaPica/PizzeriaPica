function mostraCarrelloTableUtente() {
        $.getJSON("MostraCarrelloServlet", function (data) {
            console.log(data);
            if (data == null || data == 'null' || data.length == 0) {
                $("#carrello-table").html("<tr id='carrelloVuoto'><th>Carrello vuoto</th></tr>");
                $("#totale").css("display", "none");
                $("#order").css("display", "none");
            }
            if (data.length != 0) {
                    $("#carrello-table").empty();
                    $("#carrello-table").append("<tr>\n" +
                        "                <th></th>\n" +
                        "                <th>Prezzo</th>\n" +
                        "                <th>Sconto</th>\n" +
                        "            </tr>");
                    let totale = 0;
                    for (let i = 0; i < data.length; i++) {
                        totale += data[i].prezzo;
                        $("#carrello-table").append("<tr class='prodotto-carrello'><td>" + data[i].quantita + " " + data[i].nome + "</td><td>" + data[i].prezzo + "</td><td>" + data[i].sconto + "%</td> <td style='color:red;cursor:pointer;' onclick='rimuoviProdottoCarrello(" + data[i].prodottoId + ")'>X</td></tr>");
                        $("#totale").html("Totale: " + totale + " &euro;");
                    }
                    $("#totale").css("display", "block");
                    $("#order").css("display", "block");
            }
}); }


function mostraCarrelloTableAnonimo() {
    $.getJSON("MostraCarrelloServlet", function (data) {
        if (data == null || data == 'null' || data.length == 0) {
            $("#carrello-table").html("<tr id='carrelloVuoto'><th>Carrello vuoto</th></tr>");
            $("#totale").css("display", "none");
            $("#order").css("display", "none");
        } else {
            $("#carrello-table").empty();
            $("#carrello-table").append("<tr>\n" +
                "                <th></th>\n" +
                "                <th>Prezzo</th>\n" +
                "                <th>Sconto</th>\n" +
                "            </tr>");
            let totale = 0;
            for (let i = 0; i < data.length; i++) {
                totale += data[i].prezzo;
                $("#carrello-table").append("<tr class='prodotto-carrello'><td>" + data[i].nome + "</td><td>" + data[i].prezzo + "</td><td>" + data[i].sconto + "%</td><td style='color:red; cursor:pointer;' onclick='rimuoviProdottoCarrello(" + data[i].id + ")'>X</td></tr></tr>");
                $("#totale").html("Totale: " + totale + " &euro;");
            }
            $("#totale").css("display", "block");
            $("#order").css("display", "block");
        }
    }); }


function rimuoviProdottoCarrello(prodottoId) {
    $.getJSON("RimuoviProdottoCarrelloServlet", {"idProdotto": prodottoId}, function(data) {
        if (data =="db")
            mostraCarrelloTableUtente();
        else
            mostraCarrelloTableAnonimo();
    });
}