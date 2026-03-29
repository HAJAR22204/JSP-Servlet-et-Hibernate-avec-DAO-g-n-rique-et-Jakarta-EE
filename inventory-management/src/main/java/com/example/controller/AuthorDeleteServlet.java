package com.example.controller;

import com.example.dao.AuthorDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/delete-author")
public class AuthorDeleteServlet extends HttpServlet {
    private AuthorDAO authorDAO;
    public void init() { authorDAO = new AuthorDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        authorDAO.removeById(Long.parseLong(req.getParameter("id")));
        res.sendRedirect("authors");
    }
}