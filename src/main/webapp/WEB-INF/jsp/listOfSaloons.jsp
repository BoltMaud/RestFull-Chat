<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: A626200
  Date: 28/11/2016
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of saloons</title>
</head>
<body>
<ul>
    <c:forEach var="saloon" items="${listOfSaloons}">
        <li>${saloon}</li>
    </c:forEach>
</ul>
</body>
</html>
