<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Отсортированный массив</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
  </head>
  <body>
    <h2>Отсортированный массив:</h2>
    <% int[] sortedNumbers = (int[]) request.getAttribute("sortedNumbers"); if
    (sortedNumbers != null) { for (int number : sortedNumbers) {
    out.print(number + " "); } } %>
    <br /><br />
    <a href="index.jsp">Сортировать другой массив</a>
  </body>
</html>
