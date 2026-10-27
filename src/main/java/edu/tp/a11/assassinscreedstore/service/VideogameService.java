package edu.tp.a11.assassinscreedstore.service;
import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.persistence.VideogameDAO;
import edu.tp.a11.assassinscreedstore.persistence.VideogameDAO_MariaDB;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VideogameService {

    private final VideogameDAO videogameDAO;

    public VideogameService(VideogameDAO videogameDAO) {
        this.videogameDAO = videogameDAO;
    }

    public List<Videogame> getAllGames() {
        return videogameDAO.findAll();
    }

    public Videogame getGameById(String id) {
        return videogameDAO.findById(id);
    }

    public Videogame createGame(Videogame videogame) {
        return videogameDAO.create(videogame);
    }

    public Videogame updateGame(Videogame videogame) {
        return videogameDAO.update(videogame);
    }

    public boolean deleteGame(String id) {
        return videogameDAO.delete(id);
    }

    public void validateGame(Videogame videogame) {
        if (videogame.getTitle() == null || videogame.getTitle().isBlank()) {
            throw new IllegalArgumentException("titre est manquant");
        }
        if (videogame.getUnitPrice().equals(BigDecimal.ZERO)) {
            throw new IllegalArgumentException("prix unitaire est manquant");
        }
    }

    public List<Videogame> searchByKeyword(String keyword) {
        if (keyword == null) return Collections.emptyList();

        keyword = keyword.trim();
        if (keyword.isBlank()) return Collections.emptyList();

        return videogameDAO.findByKeyword(keyword);
    }

    public List<List<Videogame>> partition(List<Videogame> list, int limit) {
        List<List<Videogame>> groups = new ArrayList<>();

        for (int i = 0; i < list.size(); i+= limit) {
            int end = Math.min(i+limit, list.size());
            groups.add(list.subList(i, end));
        }
        return groups;
    }

}

