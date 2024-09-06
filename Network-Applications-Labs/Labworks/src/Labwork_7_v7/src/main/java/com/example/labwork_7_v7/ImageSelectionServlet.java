package com.example.labwork_7_v7;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class ImageSelectionServlet extends HttpServlet {

  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    String category = request.getParameter("category");
    request.setAttribute("category", category);
    request.getRequestDispatcher("result.jsp").forward(request, response);
  }
}
