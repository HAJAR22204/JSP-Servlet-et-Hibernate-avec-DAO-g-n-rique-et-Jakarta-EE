package com.example.controller;

import com.example.dao.AuthorDAO;
import com.example.model.Author;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorListServlet extends HttpServlet {
    private AuthorDAO authorDAO;
    public void init() { authorDAO = new AuthorDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<Author> authors = authorDAO.fetchAll();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("author-list.jsp").forward(req, res);
    }
}