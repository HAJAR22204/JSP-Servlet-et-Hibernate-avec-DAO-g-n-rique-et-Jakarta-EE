package com.example.controller;

import com.example.dao.AuthorDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-author")
public class AuthorUpdateServlet extends HttpServlet {
    private AuthorDAO authorDAO;
    public void init() { authorDAO = new AuthorDAO(); }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        authorDAO.fetchById(id).ifPresent(author -> {
            author.setFullName(req.getParameter("fullName"));
            author.setNationality(req.getParameter("nationality"));
            author.setBirthYear(Integer.parseInt(req.getParameter("birthYear")));
            author.setEmail(req.getParameter("email"));
            authorDAO.modify(author);
        });
        res.sendRedirect("authors");
    }
}