<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>
<h1>Registration:</h1>
<h3 style="color: green">${message}</h3>
<form method="post" action="${pageContext.request.contextPath}/product/addition">
    Input product name<input type="text" name="name">
    <br/>
    Input product price<input type="text" name="price">
    <br/>
    <button type="submit">Add product</button>
</form>
</body>
</html>
