package edu.tp.a11.assassinscreedstore.control;

import edu.tp.a11.assassinscreedstore.model.Cart;
import edu.tp.a11.assassinscreedstore.model.CartItem;
import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.model.Videogame;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends BaseServlet {
    private static final String GUEST_CART = "guestCart";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cart cart = getCart(request);
        request.setAttribute("cart", cart);
        request.setAttribute("cartCount", cart.getCartItems().size());

        String action = request.getParameter("action");
        if ("select".equals(action) || "add".equals(action)) {
            // The product-details page currently uses action=add without a platform.
            // Show the platform picker first; adding is performed by its POST form.
            request.setAttribute("gameId", request.getParameter("gameId"));
            request.getRequestDispatcher("/WEB-INF/view/PageChoixPlateforme.jsp")
                    .forward(request, response);
            return;
        }

        if ("continue".equals(action)) {
            response.sendRedirect(request.getContextPath() + "/videogames");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/view/PageCart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "add":
                addItem(request, response);
                return;
            case "remove":
                removeItem(request, response);
                return;
            case "checkout":
                clearCart(request);
                response.sendRedirect(request.getContextPath() + "/cart?action=view&success=true");
                return;
            case "continue":
                response.sendRedirect(request.getContextPath() + "/videogames");
                return;
            case "select":
                request.setAttribute("gameId", request.getParameter("gameId"));
                Cart selectedCart = getCart(request);
                request.setAttribute("cart", selectedCart);
                request.setAttribute("cartCount", selectedCart.getCartItems().size());
                request.getRequestDispatcher("/WEB-INF/view/PageChoixPlateforme.jsp")
                        .forward(request, response);
                return;
            default:
                Cart cart = getCart(request);
                request.setAttribute("cart", cart);
                request.setAttribute("cartCount", cart.getCartItems().size());
                request.getRequestDispatcher("/WEB-INF/view/PageCart.jsp").forward(request, response);
        }
    }

    private Cart getCart(HttpServletRequest request) {
        User user = getLoggedInUser(request);
        if (user != null) {
            Cart cart = cartService.loadCart(user.getUserId());
            if (cart != null) {
                return cart;
            }
            cart = new Cart(user.getUserId());
            cart.setTotalPrice(BigDecimal.ZERO);
            return cart;
        }

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(GUEST_CART);
        if (cart == null) {
            cart = new Cart();
            cart.setCartId("guest-" + UUID.randomUUID());
            cart.setTotalPrice(BigDecimal.ZERO);
            session.setAttribute(GUEST_CART, cart);
        }
        return cart;
    }

    private User getLoggedInUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedInUser");
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String gameId = request.getParameter("gameId");
        String platform = request.getParameter("plateforme");
        if (gameId == null || gameId.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing gameId");
            return;
        }
        if (platform == null || platform.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/cart?action=select&gameId=" + gameId);
            return;
        }

        User user = getLoggedInUser(request);
        if (user != null) {
            cartService.addItem(user.getUserId(), gameId, platform);
        } else {
            Cart cart = getCart(request);
            Videogame game = videogameService.getGameById(gameId);
            if (game == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Game not found");
                return;
            }

            boolean alreadyInCart = cart.getCartItems().stream()
                    .anyMatch(item -> gameId.equals(item.getGameId()));
            if (!alreadyInCart) {
                // The selected platform belongs to this cart item, not the catalog record.
                game.setPlatform(platform);
                CartItem item = new CartItem(
                        cart.getCartId(), gameId, platform, game, game.getUnitPrice());
                item.setCartItemId(UUID.randomUUID().toString());
                cart.getCartItems().add(item);
                cart.setTotalPrice(cart.calculateCartTotal());
            }
        }

        response.sendRedirect(request.getContextPath() + "/cart?action=view");
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String cartItemId = request.getParameter("cartItemId");
        User user = getLoggedInUser(request);
        if (user != null) {
            cartService.removeItem(user.getUserId(), cartItemId);
        } else {
            Cart cart = getCart(request);
            cart.getCartItems().removeIf(item -> item.getCartItemId().equals(cartItemId));
            cart.setTotalPrice(cart.calculateCartTotal());
        }
        response.sendRedirect(request.getContextPath() + "/cart?action=view");
    }

    private void clearCart(HttpServletRequest request) {
        User user = getLoggedInUser(request);
        if (user != null) {
            cartService.clearCart(user.getUserId());
        } else {
            Cart cart = getCart(request);
            cart.getCartItems().clear();
            cart.setTotalPrice(BigDecimal.ZERO);
        }
    }
}
