package edu.tp.a11.assassinscreedstore.control;



import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author zacch
 */
@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends BaseServlet {

    // shows the signup page
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/signupPage.jsp").forward(request, response);
    }

    // handles the form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");


        try {
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setPasswordHash(password);

            // the register methode encrypts the String passwrod before storing it in the database
            userService.register(newUser);

            // redirect to login page with a success message
            HttpSession session = request.getSession(true);
            Locale locale = Locale.getDefault();
            String success = ResourceBundle.getBundle("app", locale).getString("SPSuccessMessage");
            session.setAttribute("successMessage", success);
            response.sendRedirect(request.getContextPath() + "/login");

        } catch (IllegalArgumentException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/signupPage.jsp").forward(request, response);
        }
    }
}
