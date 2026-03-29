package com.example.controller;

import com.example.dao.AuthorDAO;
import com.example.model.Author;
import com.example.util.ValidationUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/create-author")
public class AuthorCreateServlet extends HttpServlet {
    private AuthorDAO authorDAO;
    public void init() { authorDAO = new AuthorDAO(); }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String fullName     = req.getParameter("fullName");
        String nationality  = req.getParameter("nationality");
        String birthYearStr = req.getParameter("birthYear");
        String email        = req.getParameter("email");

        Integer birthYear = (birthYearStr != null && !birthYearStr.isEmpty())
                ? Integer.parseInt(birthYearStr.trim()) : null;

        Author author = new Author(fullName, nationality, birthYear, email);

        // Validation
        Map<String, String> errors = ValidationUtil.validate(author);
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("author", author);
            req.getRequestDispatcher("author-form.jsp").forward(req, res);
            return;
        }

        authorDAO.persist(author);
        res.sendRedirect("authors");
    }
}