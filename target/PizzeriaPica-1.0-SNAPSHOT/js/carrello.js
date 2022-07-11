
    function mostraCarrelloAside() {

    $.getJSON("MostraCarrelloServlet", function (data) {
        $("#prodottiCarrelloDb").empty();
        let totale = 0;
        for (let i = 0; i < data.length; i++) {
            totale += data[i].prezzo;
            $("#prodottiCarrelloDb").append("<li>" + data[i].nome + data[i].prezzo + data[i].sconto + data[i].quantita + " </li>");
            $("#totaleSession").html("Totale:" + totale);
        }
    });

    }


