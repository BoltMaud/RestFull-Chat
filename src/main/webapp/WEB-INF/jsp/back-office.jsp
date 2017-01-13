<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: A626200
  Date: 28/11/2016
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BackOffice Dashboard</title>
</head>
<body>
<h1 id="salonH1">Backoffice dashboard</h1>
<div class="back-office">
<ul>
    <li><h2 class="title">Get list of saloons available</h2>
        <iframe style="height: 300px; border:1px gray; width: 100%;"
                name="_list"
                src="<c:url value="/saloon/list"/>" scrolling="auto" >
        </iframe>
    </li>
    <li><h2  class="title">Get list of users registered</h2>
        <iframe style="height: 300px; border:1px gray ; width: 100%;"
                name="_users"
                src="<c:url value="/user/list"/>" scrolling="auto" >
        </iframe>
    </li>
</ul>
</div>
</body>
</html>
