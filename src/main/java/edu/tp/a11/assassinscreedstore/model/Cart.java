/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tp.a11.assassinscreedstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO pour le panier
 * @author zacch
 * @since 22 mai 2026
 */
public class Cart {
    
    // Attributs
    private static Cart instance;
    
    private String cartId;
    private String userId;
    private BigDecimal totalPrice;
    private List<CartItem> cartItems;

    
    // Constructeur
    //necessaire pour le DAO
    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    //Business constructeur: utilisant quand on crée un nouveau cart pour le user
    public Cart(String userId) {
        this .userId = userId;
        this.cartItems = new ArrayList<>();
    }

    // Methodes
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() { return totalPrice;}

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal calculateCartTotal() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {

                if (cartItem.getTotal() != null) {
                    totalPrice = totalPrice.add(cartItem.getTotal());
                }
            }
        }
        this.totalPrice = totalPrice;
        return totalPrice;
    }

    @Override
    public String toString() {
        return String.format(
                "Cart{cartId=%s, userId=%s, cartTotal=%.2f}",
                cartId,  userId, totalPrice
        );
    }
}
