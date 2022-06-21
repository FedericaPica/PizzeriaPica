function deleteCategoria(id) {
    $.get('EliminaCategoriaServlet', {
        "categoriaId": id
    }, function(data) {
        items = JSON.parse(data);
        Swal.fire({
            icon: items.type.toLowerCase(),
            title: items.title,
            text: items.body
        });
        $('#categoria' + id).remove();
    });
}

function myPopup(myURL, title, myWidth, myHeight) {
    var left = (screen.width - myWidth) / 2;
    var top = (screen.height - myHeight) / 4;
    var myWindow = window.open(myURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + myWidth + ', height=' + myHeight + ', top=' + top + ', left=' + left);
}

$(document).ready(function() {

    //Categorie:

    $("#categorieA").on("click", function() {
        $("#mostra").empty()
        $.getJSON("MostraCategorieServlet", function(data) {
            $("#mostra").append("<tr> <th>Categoria</th> <th>Priorit&agrave;</th> <th> </th></tr>");
            for (let i = 0; i < data.length; i++) {

                $("#mostra").append("<tr id='categoria" + data[i].id + "'> <td>" + data[i].nome + "</td> <td>" + data[i].priority + "</td>" +

                    "<td style='max-width: 80px'> <button id='deleteCategoria" + data[i].id + "'> Elimina </button>" +
                    "<button id='modificaCategoria" + data[i].id + "'> Modifica </button>" +
                    "<button id='visualizzaCategoria" + data[i].id + "'> Mostra prodotti </button> </td> </tr>");

                $('#deleteCategoria' + data[i].id).click(function() {
                    deleteCategoria(data[i].id)
                });


                $('#modificaCategoria' + data[i].id).click(function() {
                    myPopup("ModificaCategoriaServlet?id=" + data[i].id, "Modifica", 600, 400);
                });

                $('#visualizzaCategoria' + data[i].id).click(function() {
                    myPopup("MostraProdottiServlet?categoria=" + data[i].nome, "Prodotti", 800, 400);
                });
            }
        });
    });

    $("#inserisciCategoria").click(function() {
        myPopup("InserisciCategoriaServlet", "Inserisci", 600, 200);
    })



});