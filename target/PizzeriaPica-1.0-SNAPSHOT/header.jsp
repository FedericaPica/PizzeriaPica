<%@ page import="model.beans.Message" %>
<link rel="stylesheet" href="css/header.css" type="text/css">
<header id="header">

<div class="slideshow-container" style="background-color: #F18F01; max-height: 10%; width:100%;">
    <div style="width:10%; float: left; margin-left:5%; margin-right:5%;">
        <img src="images/ppica.png" style="width: 100%; height:auto;">
    </div>
        <div class="mySlides">
            <img class="header-img" src="images/header.png" style="width: 100%">
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