package edu.tp.a11.assassinscreedstore.service;

import edu.tp.a11.assassinscreedstore.model.Cart;
import edu.tp.a11.assassinscreedstore.model.CartItem;
import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.persistence.CartDAO;
import edu.tp.a11.assassinscreedstore.persistence.CartItemDAO;
import edu.tp.a11.assassinscreedstore.persistence.UserDAO;
import edu.tp.a11.assassinscreedstore.persistence.VideogameDAO;

import java.math.BigDecimal;
import java.util.List;

public class CartService {

    private CartDAO cartDAO;
    private VideogameDAO videogameDAO;
    private CartItemDAO cartItemDAO;


    public CartService(CartDAO cartDAO,VideogameDAO videogameDAO, CartItemDAO cartItemDAO) {
        this.cartDAO = cartDAO;
        this.videogameDAO = videogameDAO;
        this.cartItemDAO = cartItemDAO;
    }

    public Cart loadCart(String userId){
       Cart cart = cartDAO.findCartByUserId(userId);
       if(cart==null)return null;

       List<CartItem> items = cartItemDAO.findByCartIdWithGames(cart.getCartId());
       cart.setCartItems(items);

       return  cart;
    }

    public Cart createCart(String userId){
       Cart cart = new Cart(userId);
       cartDAO.create(cart);
       return cart;
    }

    public Cart loadOrCreateCart(String userId){
        Cart cart = loadCart(userId);
        if(cart == null){
            cart = createCart(userId);
        }
        return cart;
    }

    public void addItem(String userId, String gameId, String platform) {
        if(userId == null || gameId==null || platform==null || platform.trim().isEmpty()) {
            throw new RuntimeException("jeux introuvables"+ gameId);
        }

        Cart cart = loadOrCreateCart(userId);
        Videogame game = videogameDAO.findById(gameId);


        CartItem itemExists = cartItemDAO.findByCartIdAndGameId(cart.getCartId(), game.getGameId());
        if(itemExists != null) {
            return;
        }

            CartItem cartItem = new CartItem(
                    cart.getCartId(),
                    game.getGameId(),
                    platform,
                    game,
                    game.getUnitPrice()
            );

        System.out.println("====== DIAGNOSTIC ======");
        System.out.println("Master Game Catalog Price: " + game.getUnitPrice());
        System.out.println("Instantiated CartItem Price: " + cartItem.getTotal());
        System.out.println("========================");

           cartItemDAO.create(cartItem); //persistence
           cart.getCartItems().add(cartItem); // en memoire
           updateTotal(cart.getCartId(), userId);
    }

    public void removeItem(String userId, String cartItemId) {
       Cart cart = loadCart(userId);
       if(cart == null){
           return;
       }

       CartItem item = cartItemDAO.findById(cartItemId);
       if(item == null){
           return;
       }
       cartItemDAO.delete(item.getCartItemId());
       cart.getCartItems().removeIf(ci -> ci.getCartItemId().equals(cartItemId));
       updateTotal(cart.getCartId(), userId);
    }

    public void clearCart(String userId) {
        Cart cart = loadCart(userId);
        if(cart == null){
            return;
        }
        cart.getCartItems().clear();
        cartItemDAO.deleteAllByCartId(cart.getCartId());
        cartDAO.updateTotalPrice(cart.getCartId(), BigDecimal.ZERO);
    }

    public void updateTotal(String cartId, String userId) {
        List<CartItem> cartItems = cartItemDAO.findByCartIdWithGames(cartId);
        Cart cart = cartDAO.findById(cartId);
        BigDecimal cartTotal = BigDecimal.ZERO;
        if (cart != null){
            cart.setCartItems(cartItems);
            cartTotal = cart.calculateCartTotal();
        }
        cartDAO.updateTotalPrice(cartId, cartTotal);
    }

    public int cartItemCount(String userId) {
        Cart cart = cartDAO.findCartByUserId(userId);
        if(cart == null) return 0;
        return cartDAO.updateCartItemCount(cart.getCartId());
    }

    public boolean IsInCart(String userId, String gameId) {
        Cart cart = loadOrCreateCart(userId);
        List<CartItem> cartItems = cart.getCartItems();
        if(cartItems == null || cartItems.isEmpty()) return false;
        for (CartItem cartItem : cartItems) {
            if(cartItem.getGameId().equals(gameId)){
                return true;
            }
        }
        return false;
    }
}
