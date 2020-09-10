<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1>Registration:</h1>
<h3 style="color: red">${message}</h3>
<form method="post" action="${pageContext.request.contextPath}/user/registration">
    Input your name<input type="text" name="name" minlength="2">
    <br/>
    Input your login<input type="text" name="login" minlength="4" maxlength="12">
    <br/>
    Input your password<input type="password" name="pass" minlength="6" maxlength="16">
    <br/>
    Repeat your password<input type="password" name="repeatedPass" minlength="6" maxlength="16">
    <br/>
    <button type="submit">Register</button>
</form>
</body>
</html>
