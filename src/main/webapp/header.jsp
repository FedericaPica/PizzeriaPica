<%@ page import="model.beans.Message" %>
<link rel="stylesheet" href="css/header.css" type="text/css">
<header id="header">

<div class="slideshow-container" style="background-color: #F18F01; max-height: 10%; width:100%;">
    <div style="width:10%; float: left;">
        <img src="images/ppica.png" style="width: 100%; height:auto;">
    </div>
        <div class="mySlides">
            <img class="header-img" src="images/locale1.jpeg" style="width: 100%">
        </div>

        <div class="mySlides">
            <img class="header-img" src="images/locale2.jpeg" style="width: 100%">
        </div>

        <div class="mySlides">
            <img class="header-img" src="images/locale3.jpeg" style="width: 100%">
        </div>

    <div class="mySlides">
        <img class="header-img" src="images/insegna.png" style="width: 100%">
    </div>



</div>
    <br>
</header>
<% Message message = (Message) request.getAttribute("message");
    if (message != null) { %>
<script>
    Swal.fire({
        icon: '<%=message.getType().toLowerCase()%>',
        title: '<%=message.getTitle()%>',
        text: '<%=message.getBody()%>'
    })

</script>

<%
    }
%>
<script>
    // let slideIndex = 0;
    // showSlides();
    // function showSlides() {
    //     let i;
    //     let slides = document.getElementsByClassName("mySlides");
    //     for (i = 0; i < slides.length; i++) {
    //         slides[i].style.display = "none";
    //     }
    //     slideIndex++;
    //     if (slideIndex > slides.length) {slideIndex = 1}
    //     slides[slideIndex-1].style.display = "block";
    //     setTimeout(showSlides, 3000);
    // }
</script>