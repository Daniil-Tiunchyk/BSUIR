package com.example.labwork_7_v13;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortServlet extends HttpServlet {

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    request.getRequestDispatcher("/index.jsp").forward(request, response);
  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    String numbers = request.getParameter("numbers");
    int[] sortedNumbers = SortUtils.sortArray(numbers);
    request.setAttribute("sortedNumbers", sortedNumbers);
    request.getRequestDispatcher("/result.jsp").forward(request, response);
  }
}
