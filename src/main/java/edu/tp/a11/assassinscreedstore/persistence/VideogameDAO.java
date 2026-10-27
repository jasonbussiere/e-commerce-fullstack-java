package edu.tp.a11.assassinscreedstore.persistence;

import edu.tp.a11.assassinscreedstore.model.Videogame;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interface qui définit le contrat pour les entités de type Videogame. Elle appartient à la couche de persistance
 * et a pour rôle d'abstraire l'accès à la base de données. Son implémentation est réalisée par une classe
 * JDBC (VideogameDAOMariaDB) et elle est utilisée par la couche service (VideogameService). Cette interface extends
 * TimestampedDAO<Videogame> et extends CrudDAO<Videogame, Integer> afin d'hériter des opérations de recherche par date de création et par date de modification,
 * ainsi que les opération CRUD génériques.
 *
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 * @since 25 mai 2026
 */

public interface VideogameDAO extends CrudDAO<Videogame, String> {
    /**
     * Récupérer le Videogame par son titre dans la base de données
     *
     * @param title le titre du Videogame
     * @return le Videogame trouvé incluant toutes les valeurs généré par la base de données
     */
    Videogame findByTitle(String title);

    /**
     * Récupérer tous les Videogame par leur genre dans la base de données
     * @param genre le genre de Videogame
     * @return une liste de tous les Videogame du genre spécifié
     */
    List<Videogame> findByGenre(String genre);

    /**
     * Récupérer tous les Videogame par leur plateforme dans la base de données
     * @param platform le plateforme du Videogame
     * @return une liste de tous les Videogames du plateforme spécifié
     */
    List<Videogame> findByPlatform(String platform);

    /**
     * Récupérer tous les Videogame inclusivement à l'intérieur d'une plage de date
     * de publication dans la base de données
     * @param start la date minimum
     * @param end la date maximum
     * @return une liste de tous les Videogame avec une date de publication à l'intérieur
     * de la plage de date spécifié
     */
    List<Videogame> findByReleaseDate(LocalDate start, LocalDate end);

    /**
     * Récupérer tous les Videogame par leur prix de détail dans la base de données
     * @param price le prix de détails suggérés par le fabricant
     * @return une liste de tous les Videogame avec le prix msrp spécifié
     */
    List<Videogame> findByUnitPrice(BigDecimal price);

    /**
     * Récupérer tous les Videogame par leur description dans la base de données
     * @param description la description du Videogame
     * @return une liste de  Videogame qui correspond à la description donnée
     */
    List<Videogame> findByDescription(String description);

    List<Videogame> findByKeyword(String keyword);

    Videogame mapRow(ResultSet rs) throws SQLException;

}
