package com.example.controller;

import com.example.dao.CategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/delete-category")
public class CategoryDeleteServlet extends HttpServlet {
    private CategoryDAO categoryDAO;
    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        categoryDAO.removeById(Long.parseLong(req.getParameter("id")));
        res.sendRedirect("categories");
    }
}