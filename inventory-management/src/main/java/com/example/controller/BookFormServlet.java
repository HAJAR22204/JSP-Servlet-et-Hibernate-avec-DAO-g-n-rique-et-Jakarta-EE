package com.example.controller;

import com.example.dao.BookDAO;
import com.example.dao.CategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/book-form")
public class BookFormServlet extends HttpServlet {
    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        bookDAO = new BookDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if ("edit".equals(req.getParameter("action"))) {
            Long id = Long.parseLong(req.getParameter("id"));
            bookDAO.fetchById(id).ifPresent(b -> req.setAttribute("book", b));
        }
        req.setAttribute("categories", categoryDAO.fetchAll());
        req.getRequestDispatcher("book-form.jsp").forward(req, res);
    }
}