package com.example.controller;

import com.example.dao.BookDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-book")
public class BookUpdateServlet extends HttpServlet {
    private BookDAO bookDAO;
    public void init() { bookDAO = new BookDAO(); }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        bookDAO.fetchById(id).ifPresent(book -> {
            book.setTitle(req.getParameter("title"));
            book.setIsbn(req.getParameter("isbn"));
            book.setGenre(req.getParameter("genre"));
            book.setPublicationYear(Integer.parseInt(req.getParameter("publicationYear")));
            book.setAvailableCopies(Integer.parseInt(req.getParameter("availableCopies")));
            bookDAO.modify(book);
        });
        res.sendRedirect("books");
    }
}