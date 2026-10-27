package edu.tp.a11.assassinscreedstore.control;


import edu.tp.a11.assassinscreedstore.model.Cart;
import edu.tp.a11.assassinscreedstore.model.CartItem;
import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.persistence.*;
import edu.tp.a11.assassinscreedstore.repository.Database;
import edu.tp.a11.assassinscreedstore.service.CartService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCartCount(request);
        User user = (User)request.getSession().getAttribute("loggedInUser");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/loginPage.jsp");
            return;
        }

        String action=request.getParameter("action");
        if(action==null){
            action="view";
        }

        switch(action){
            case "continue": {
                // product list or last product details??
                response.sendRedirect(request.getContextPath()+"/videogames");
                return;
            }
            case "select": {
                String gameId=request.getParameter("gameId");
                request.setAttribute("gameId",gameId);
                request.getRequestDispatcher("/WEB-INF/view/PageChoixPlateforme.jsp").forward(request, response);
                return;
            }
            case "view":
            default: {
                Cart cart = cartService.loadCart(user.getUserId());
                System.out.println(cart.getTotalPrice());
                request.setAttribute("cart", cart);
                request.getRequestDispatcher("/WEB-INF/view/PageCart.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCartCount(request);
        User user = (User)request.getSession().getAttribute("loggedInUser");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/loginPage.jsp");
            return;
        }

        String action=request.getParameter("action");
        if(action==null){
            action="view";
        }
        switch(action){
            case "add": {
                String gameId=request.getParameter("gameId");
                String selectedPlatform=request.getParameter("plateforme");

                boolean isDuplicate = cartService.IsInCart(user.getUserId(), gameId);
                if(isDuplicate){
                    response.sendRedirect(request.getContextPath()+"/videogames?error=duplicate");
                    return;
                }
                cartService.addItem(user.getUserId(),gameId, selectedPlatform);
                response.sendRedirect("cart?action=view");
                return;
            }
            case "remove": {
                String cartItemId =request.getParameter("cartItemId");
                cartService.removeItem(user.getUserId(),cartItemId);
                response.sendRedirect("cart?action=view");
                return;
            }

            case "checkout": {
                cartService.clearCart(user.getUserId());
                response.sendRedirect(request.getContextPath()+"/cart?action=view&success=true");
                return;
            }

            }
        }
    }