<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Выбор изображения</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
  </head>
  <body>
    <form action="ImageSelectionServlet" method="post">
      <select name="category">
        <option value="nature">Природа</option>
        <option value="fullstack">А дальше-то что?</option>
        <option value="info">Информация</option>
      </select>
      <input type="submit" value="Выбрать" />
    </form>
  </body>
</html>
