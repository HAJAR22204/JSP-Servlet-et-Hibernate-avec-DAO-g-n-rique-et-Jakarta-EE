package com.example.controller;

import com.example.dao.CategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/update-category")
public class CategoryUpdateServlet extends HttpServlet {
    private CategoryDAO categoryDAO;
    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        categoryDAO.fetchById(id).ifPresent(cat -> {
            cat.setName(req.getParameter("name"));
            cat.setDescription(req.getParameter("description"));
            categoryDAO.modify(cat);
        });
        res.sendRedirect("categories");
    }
}