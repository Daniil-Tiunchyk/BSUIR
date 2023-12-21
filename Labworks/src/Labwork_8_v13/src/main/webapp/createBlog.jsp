<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Blog</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<form method="post" action="BlogServlet">
    Заголовок: <input type="text" name="title"><br/>
    Содержание: <textarea name="content"></textarea><br/>
    <input type="submit" value="Submit">
</form>
</body>
</html>
