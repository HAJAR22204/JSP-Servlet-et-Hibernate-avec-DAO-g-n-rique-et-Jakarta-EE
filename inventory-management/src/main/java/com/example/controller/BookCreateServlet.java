package com.example.controller;

import com.example.dao.BookDAO;
import com.example.dao.CategoryDAO;
import com.example.model.Book;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/create-book")
public class BookCreateServlet extends HttpServlet {
    private BookDAO bookDAO;
    private CategoryDAO categoryDAO;

    public void init() {
        bookDAO = new BookDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String title   = req.getParameter("title");
        String isbn    = req.getParameter("isbn");
        String genre   = req.getParameter("genre");
        Integer year   = Integer.parseInt(req.getParameter("publicationYear"));
        Integer copies = Integer.parseInt(req.getParameter("availableCopies"));
        String categoryIdStr = req.getParameter("categoryId");

        Book book = new Book(title, isbn, genre, year, copies);

        if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
            categoryDAO.fetchById(Long.parseLong(categoryIdStr))
                    .ifPresent(book::setCategory);
        }

        bookDAO.persist(book);
        res.sendRedirect("books");
    }
}