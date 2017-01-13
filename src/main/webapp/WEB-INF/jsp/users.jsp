<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List des utilisateurs</title>
</head>
<body>
    <div>
        <ul>
        <c:forEach var="user" items="${users}">
            <li>${user.name}</li>
        </c:forEach>
        </ul>
    </div>
</body>
</html>
