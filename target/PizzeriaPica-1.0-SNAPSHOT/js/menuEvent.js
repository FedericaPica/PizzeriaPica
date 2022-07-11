$(document).ready(function() {
    function MyCategorie (item) {
       $("#categorie").append("<li> <a href='index.jsp#name" + item.nome + "'>" + item.nome + "</a></li>");
    }


    $("#categorie").hide();
    $("#menu").click(function() {
            $("#categorie").empty()
            $.getJSON("MostraCategorieServlet", function(data) {
                data.forEach(MyCategorie);
                $("#categorie").slideToggle("slow", function(){
                });
            })

    });

    // let section = document.getElementById("section");
    // let nav = document.getElementById("navBar");
    //
    // let navPos = nav.getBoundingClientRect().top;
    //
    // window.addEventListener("scroll", e => {
    //     let scrollPos = window.scrollY;
    //     if (scrollPos > navPos) {
    //         nav.classList.add('sticky');
    //         section.classList.add('navOffsetMargin');
    //     } else {
    //         nav.classList.remove('sticky');
    //         section.classList.remove('navOffsetMargin');
    //     }
    // });
})
