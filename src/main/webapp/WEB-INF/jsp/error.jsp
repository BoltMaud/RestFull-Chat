<%--
  Created by IntelliJ IDEA.
  User: A626200
  Date: 27/11/2016
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/error.css">
</head>
<body>
    <h1 class="errors">${errorMsg}</h1>
    <a href="${pageContext.request.contextPath}/">Return</a>
</body>
</html>
