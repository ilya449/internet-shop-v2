<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <h1>Registration:</h1>
    <h3 style="color: red">${message}</h3>
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/registration">
        <div class="form-group">
            <label>Input your name
                <input type="text" name="name" minlength="2">
            </label>
        </div>
        <br/>
        <div class="form-group">
            <label>Input your login
                <input type="text" name="login" minlength="4" maxlength="12">
            </label>
        </div>
        <br/>
        <div class="form-group">
            <label>Input your password
                <input type="password" name="pass" minlength="6" maxlength="16">
            </label>
        </div>
        <br/>
        <div class="form-group">
            <label>Repeat your password
                <input type="password" name="repeatedPass" minlength="6" maxlength="16">
            </label>
        </div>
        <br/>
        <button class="btn-primary btn-lg" type="submit">Register</button>
    </form>
</div>
</body>
</html>
