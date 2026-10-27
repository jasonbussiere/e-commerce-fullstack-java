package edu.tp.a11.assassinscreedstore.control;


import edu.tp.a11.assassinscreedstore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 *
 * @author zacch
 */
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends BaseServlet {

    // Brings back to the login page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            request.getRequestDispatcher("/WEB-INF/view/loginPage.jsp").forward(request, response);
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.login(email, password);

        if (user == null)  {
            request.setAttribute("errorMessage", "LPErrorMessage");
            request.getRequestDispatcher("/WEB-INF/view/loginPage.jsp").forward(request, response);
            return;
        }

        session.setAttribute("loggedInUser", user);
        response.sendRedirect("home");

    }
}
