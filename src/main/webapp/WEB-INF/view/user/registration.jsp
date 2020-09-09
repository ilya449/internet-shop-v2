<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration:</h1>
<h3 style="color: red">${message}</h3>
<form method="post" action="${pageContext.request.contextPath}/user/registration">
    Input your name<input type="text" name="name">
    <br/>
    Input your login<input type="text" name="login">
    <br/>
    Input your password<input type="password" name="pass">
    <br/>
    Repeat your password<input type="password" name="repeatedPass">
    <br/>
    <button type="submit">Register</button>
</form>
</body>
</html>
