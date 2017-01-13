<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: a626200
  Date: 24/11/2016
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List</title>
    <meta HTTP-EQUIV="Refresh" CONTENT="5">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body class="content">
    <div >
        <%-- on note le salon, valeur qui peut changer d'une request à l'autre --%>
        <h1 id="salonH1">Saloon : ${salon}</h1>
        <!-- on affiche que celle de notre salon en question -->
        <c:forEach var="message" items="${listMessages}">
            <p>${message.author.name} : ${message.text}<br/></p>
        </c:forEach>
        </p>
    </div>
</body>
</html>
