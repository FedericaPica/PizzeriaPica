<%@ page import="model.beans.Message" %>
<link rel="stylesheet" href="css/header.css" type="text/css">
<header>
    <div>
        <img class="fotohead" src="images/locale3.jpeg">
        <img  class="fotohead" src="images/locale1.jpeg">
        <span id="name">Pizzeria Pica</span>
        <img class="fotohead" src="images/insegna.png">
        <img class="fotohead" src="images/locale2.jpeg">
    </div>
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