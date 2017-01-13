<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <a href="<c:url value="/logout"/>">Log out</a><br>
    <iframe style="height: 500px; border:1px solid black; width: 100%;"
            name="_message"
            src="${pageContext.request.contextPath}/saloon/${saloon}/messages" scrolling="auto" >
    </iframe>

    <div class="chatForm">
        <form action="${pageContext.request.contextPath}/saloon/${saloon}/message"
              method="post" target="_message" >
            <label for="message">Type your message : </label>
            <input type="text" id="message" name="message" />
            <input type="hidden" name="salon" value="${saloon}">
            <input type="hidden" name="pseudo" value="${username}">
            <input type="submit" value="Send"/>
        </form>
    </div>
</body>
</html>

