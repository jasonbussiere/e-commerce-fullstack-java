package edu.tp.a11.assassinscreedstore.control;

import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VideogameServlet", value = "/videogames")
public class VideogameServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        loadCartCount(request);

        String keyword = request.getParameter("searchInput");

        //Case1 - user fait une recherche
        if (keyword != null && !keyword.trim().isEmpty()) {
            keyword = keyword.trim().toLowerCase();
            List<Videogame> results = videogameService.searchByKeyword(keyword.trim());

            request.setAttribute("searchResults", results);
            request.setAttribute("keyword", keyword);
            request.getRequestDispatcher("/WEB-INF/view/searchResults.jsp").forward(request, response);
            return;
        }

    //Case2 - user click sur un lien
    List<Videogame> games = videogameService.getAllGames();
    List<List< Videogame>> groupsOf3 = videogameService.partition(games, 3);
    request.setAttribute("groupsOf3", groupsOf3);
    request.getRequestDispatcher("/WEB-INF/view/Produits.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}