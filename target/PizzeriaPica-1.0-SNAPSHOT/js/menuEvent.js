$(document).ready(function() {
    $("#categorie").hide();
    $("#menu").click(function() {
            $("#categorie").load("http://localhost:8080/PizzeriaPica_war_exploded/MenuServlet", function(data) {
                $("#categorie").slideToggle("slow", function(){
             });
         });

    });
})
