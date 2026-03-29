package com.example.controller;

import com.example.dao.BookDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/delete-book")
public class BookDeleteServlet extends HttpServlet {
    private BookDAO bookDAO;
    public void init() { bookDAO = new BookDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        bookDAO.removeById(Long.parseLong(req.getParameter("id")));
        res.sendRedirect("books");
    }
}