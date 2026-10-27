package edu.tp.a11.assassinscreedstore.model;

//javadoc todo

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author zacch
 * @since 22 mai 2026
 */
public class Videogame {

    // Attributs
    private String gameId;
    private String title;
    private String genre;
    private String platform;
    private String description;
    private BigDecimal unitPrice;
    private LocalDate releaseDate;
    private String imageUrl;
    private String wikiUrl;

    // Constructeurs
    //Necessaire pour le DAO
    public Videogame() {}

    //Business constructeur: utiliser pour ajouter un nouveau jeu
    public Videogame(String title, String genre, String plateform, String description,
                     BigDecimal price, LocalDate releaseDate, String imageUrl, String wikiUrl) {
        this.title = title;
        this.unitPrice = price;
        this.genre = genre;
        this.platform = plateform;
        this.description = description;
        this.releaseDate = releaseDate;
        this.imageUrl = imageUrl;
        this.wikiUrl = wikiUrl;
    }

    // Methodes
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal price) {
        this.unitPrice = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    @Override
    public String toString() {
        return String.format("VideoGame{gameId= %s, title= %s genre= %s, platform= %s, description= %s," +
                        " price= %.2f, releaseDate= %s, imageUrl= %s},"
                ,gameId, title, genre, platform, description, unitPrice, releaseDate, imageUrl);
    }
}
