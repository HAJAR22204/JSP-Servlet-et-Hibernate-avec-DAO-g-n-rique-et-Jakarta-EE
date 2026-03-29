package com.example.controller;

import com.example.dao.BookDAO;
import com.example.model.Book;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
    private BookDAO bookDAO;
    public void init() { bookDAO = new BookDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Book> books = (keyword != null && !keyword.trim().isEmpty())
                ? bookDAO.searchBooks(keyword)
                : bookDAO.fetchAll();
        req.setAttribute("books", books);
        req.setAttribute("keyword", keyword);
        req.getRequestDispatcher("book-list.jsp").forward(req, res);
    }
}