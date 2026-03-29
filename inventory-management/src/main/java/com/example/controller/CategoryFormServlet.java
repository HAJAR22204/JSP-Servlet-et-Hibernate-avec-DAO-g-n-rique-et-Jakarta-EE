package com.example.controller;

import com.example.dao.CategoryDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/category-form")
public class CategoryFormServlet extends HttpServlet {
    private CategoryDAO categoryDAO;
    public void init() { categoryDAO = new CategoryDAO(); }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if ("edit".equals(req.getParameter("action"))) {
            Long id = Long.parseLong(req.getParameter("id"));
            categoryDAO.fetchById(id).ifPresent(c -> req.setAttribute("category", c));
        }
        req.getRequestDispatcher("category-form.jsp").forward(req, res);
    }
}