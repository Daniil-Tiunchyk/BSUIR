<%@ page import="java.util.List" %>
<%@ page import="com.example.labwork_8_v13.Blog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Blogs</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<br/><br/>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Заголовок</th>
        <th>Содержание</th>
    </tr>
    <%
        List<Blog> listBlog = (List<Blog>) request.getAttribute("listBlog");
        System.out.println(listBlog);
        if (!(listBlog == null))
            for (Blog blog : listBlog) {
    %>
    <tr>
        <td><%= blog.getId() %>
        </td>
        <td><%= blog.getTitle() %>
        </td>
        <td><%= blog.getContent() %>
        </td>
    </tr>
    <% } %>
</table>
<a href="createBlog.jsp">Создать новый пост</a>
</body>
</html>
