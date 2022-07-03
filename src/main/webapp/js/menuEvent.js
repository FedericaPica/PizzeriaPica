$(document).ready(function() {
    function MyCategorie (item) {
       $("#categorie").append("<li> <a href='index.jsp#name" + item.nome + "'>" + item.nome + "</a></li>");
    }


    $("#categorie").hide();
    $("#menu").click(function() {
            $.getJSON("MostraCategorieServlet", function(data) {
                data.forEach(MyCategorie);
                $("#categorie").slideToggle("slow", function(){
                });
            })

    });
})
