package com.example.labwork_8_v13;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {

    private BlogDAO blogDAO;

    public void init() {
        blogDAO = new BlogDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Blog newBlog = new Blog(title, content);
        try {
            blogDAO.insertBlog(newBlog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("listBlogs");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> listBlog = blogDAO.selectAllBlogs();
        System.out.println(Arrays.toString(listBlog.toArray()));
        request.setAttribute("listBlog", listBlog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listBlogs.jsp");
        dispatcher.forward(request, response);
    }
}
