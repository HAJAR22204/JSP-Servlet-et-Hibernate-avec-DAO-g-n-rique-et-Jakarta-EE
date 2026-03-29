package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/create-category")
public class CategoryCreateServlet extends HttpServlet {
    private CategoryDAO categoryDAO;
    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String name        = req.getParameter("name");
        String description = req.getParameter("description");
        categoryDAO.persist(new Category(name, description));
        res.sendRedirect("categories");
    }
}