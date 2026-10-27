package edu.tp.a11.assassinscreedstore.control;


import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "HomeServlet", value = "/home")
public class HomeServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("loggedInUser");
        if(user == null) {
            response.sendRedirect("login");
            return;
        }
        loadCartCount(request);
        request.getRequestDispatcher("/WEB-INF/view/PageAcceuil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}