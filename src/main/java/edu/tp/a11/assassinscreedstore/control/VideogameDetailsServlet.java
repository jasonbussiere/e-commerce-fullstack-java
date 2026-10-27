package edu.tp.a11.assassinscreedstore.control;

import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "VideogameDetailsServlet", value = "/videogames/details")
public class VideogameDetailsServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("loggedInUser");
        loadCartCount(request);

        String idParam = request.getParameter("id");
        Videogame videogame = videogameService.getGameById(idParam);

        request.setAttribute("videogame", videogame);
        request.getRequestDispatcher("/WEB-INF/view/videogameDetails.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}