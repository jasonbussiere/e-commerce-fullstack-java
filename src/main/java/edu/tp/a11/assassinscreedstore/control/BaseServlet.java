package edu.tp.a11.assassinscreedstore.control;

import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.persistence.*;
import edu.tp.a11.assassinscreedstore.repository.Database;
import edu.tp.a11.assassinscreedstore.service.CartService;
import edu.tp.a11.assassinscreedstore.service.UserService;
import edu.tp.a11.assassinscreedstore.service.VideogameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {
    protected CartService cartService;
    protected VideogameService videogameService;
    protected UserService userService;

    public void init() {
        Database db = Database.getInstance();
        UserDAO userDAO = new UserDAO_MariaDB(db);
        VideogameDAO videogameDAO = new VideogameDAO_MariaDB(db);
        CartItemDAO cartItemDAO = new CartItemDAO_MariaDB(db, videogameDAO);
        CartDAO cartDAO = new CartDAO_MariaDB(db);

        videogameService = new VideogameService(videogameDAO);
        cartService = new CartService(cartDAO, videogameDAO, cartItemDAO);
        userService = new UserService(userDAO);
    }

    protected void loadCartCount(HttpServletRequest request) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("loggedInUser");
        int cartCount = cartService.cartItemCount(user.getUserId());
        request.setAttribute("cartCount", cartCount);
    }
//    //test purpose only
//    protected String getOrCreateTestUser(HttpServletRequest request) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String userId = (String) session.getAttribute("userId");
//        if (userId == null) {
//            userId = "b2e4d3c5-1a22-4f9b-9c8d-23c45d67e890";
//            session.setAttribute("userId", userId);
//        }
//        return userId;
//    }
}
