<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<h1>Registration:</h1>
<h3 style="color: green">${message}</h3>
<h3 style="color: red">${invalidDataMessage}</h3>
<form method="post" action="${pageContext.request.contextPath}/product/add">
    Input product name<input type="text" name="name" minlength="1">
    <br/>
    Input product price<input type="text" name="price" min="1">
    <br/>
    <button type="submit">Add product</button>
</form>
<a href="${pageContext.request.contextPath}/">Go to the main page</a>
</body>
</html>
