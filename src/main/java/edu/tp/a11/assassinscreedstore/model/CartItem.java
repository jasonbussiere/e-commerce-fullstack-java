/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.tp.a11.assassinscreedstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * POJO pour les items du panier
 * @author zacch
 * @since 22 mai 2026
 */
public class CartItem {
    
    // Attributs
    private String cartItemId;
    private String cartId;
    private String gameId;
    private String platform;
    private Videogame videogame;
    private BigDecimal total;

    // Constructeurs
    //Necessaire pour le DAO
    public CartItem() {}

    //Business constructeur: utiliser quand on ajoute un jeu au cart
    public CartItem(String cartId, String gameId, String platform, Videogame videogame, BigDecimal total) {
       this.cartId = cartId;
       this.gameId = gameId;
       this.platform = platform;
       this.videogame = videogame;
       this.total = total;
    }
    
    
    // Methodes

    public String getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(String cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal unitPrice) {
        this.total = unitPrice;
    }

    public Videogame getVideogame() {
        return videogame;
    }

    public void setVideogame(Videogame videogame) {
        this.videogame = videogame;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return String.format(
            "CartItem{cartItemId=%s, cartId=%s, gameId=%s, gameTitle=%s total=%.2f",
            cartItemId, cartId, gameId, videogame.getTitle(), total
        );
    }
}
