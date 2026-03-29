package com.example.controller;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryListServlet extends HttpServlet {
    private CategoryDAO categoryDAO;
    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<Category> categories = categoryDAO.fetchAll();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("category-list.jsp").forward(req, res);
    }
}