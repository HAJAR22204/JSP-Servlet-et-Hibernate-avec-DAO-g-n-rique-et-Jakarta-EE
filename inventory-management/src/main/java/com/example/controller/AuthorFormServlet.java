package com.example.controller;

import com.example.dao.AuthorDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/author-form")
public class AuthorFormServlet extends HttpServlet {
    private AuthorDAO authorDAO;
    public void init() { authorDAO = new AuthorDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if ("edit".equals(req.getParameter("action"))) {
            Long id = Long.parseLong(req.getParameter("id"));
            authorDAO.fetchById(id).ifPresent(a -> req.setAttribute("author", a));
        }
        req.getRequestDispatcher("author-form.jsp").forward(req, res);
    }
}