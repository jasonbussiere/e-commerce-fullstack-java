/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.tp.a11.assassinscreedstore.control;

import java.io.IOException;
import java.io.PrintWriter;

import edu.tp.a11.assassinscreedstore.model.User;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
     * Servlet de traitement du paiement (checkout).
     *
     * <p>
     * Ce servlet gère la finalisation de la commande d'un utilisateur.
     * Lorsqu'une requête POST est reçue, il invalide la session courante afin
     * de vider le panier, puis redirige l'utilisateur vers la page
     * d'accueil.</p>
     *
     * @author Bruno, Zacchary, Jason
     */
public class TraitementCheckout extends BaseServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Traite les requêtes HTTP POST soumises lors du checkout.
     *
     * <p>Étapes :</p>
     * <ol>
     *   <li>Récupère la session existante sans en créer une nouvelle.</li>
     *   <li>Si une session active existe, elle est invalidée (panier vidé).</li>
     *   <li>Redirige l'utilisateur vers {@code /PageAcceuil.jsp}.</li>
     * </ol>
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("loggedInUser");
        cartService.clearCart(user.getUserId());
        response.sendRedirect(request.getContextPath() + "/PageAcceuil.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
