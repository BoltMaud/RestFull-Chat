<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My website</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
    <h1>Welcome to my Chat Application !</h1>

    <div id="connexionDiv">
    <form method="POST" action="${pageContext.request.contextPath}/user"  class="loginForm">
        <label for="username" >Sign in : </label>
        <input type="text"
                    id="username" name="username" />
        <input type="submit" value="Validate"/>
    </form>
        <br>
    <hr color="silver"  style="width:350px;" />
    <form method="POST" action="${pageContext.request.contextPath}/interface"  class="loginForm" >
        <label for="pseudo" >Type your pseudo : </label>
        <input type="text" id="pseudo" name="pseudo" /><br>
        <label for="saloon" >Saloon : </label><br>
        <input type="text"  id="saloon" name="saloon"/>
        <input type="submit" value="Connection"/>
    </form>
    </div>

</body>
</html>