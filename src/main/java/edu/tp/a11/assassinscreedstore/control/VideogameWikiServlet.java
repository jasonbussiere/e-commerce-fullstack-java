package edu.tp.a11.assassinscreedstore.control;


import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.persistence.*;
import edu.tp.a11.assassinscreedstore.repository.Database;
import edu.tp.a11.assassinscreedstore.service.CartService;
import edu.tp.a11.assassinscreedstore.service.VideogameService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "VideogameWikiServlet", value = "/videogameWiki")
public class VideogameWikiServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCartCount(request);

        List<Videogame> games = videogameService.getAllGames();
        String lang="en";
        String baseUrl = "https://"+lang+".wikipedia.org/wiki";
        request.setAttribute("baseUrl", baseUrl);
        request.setAttribute("games", games);
        request.getRequestDispatcher("/WEB-INF/view/Wiki.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}