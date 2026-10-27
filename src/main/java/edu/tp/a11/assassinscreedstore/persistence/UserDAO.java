package edu.tp.a11.assassinscreedstore.persistence;


import edu.tp.a11.assassinscreedstore.model.User;

import java.util.List;

/**
 * Interface qui définit le contrat pour les entités de type User. Elle appartient à la couche de persistance
 * et a pour rôle d'abstraire l'accès à la base de données. Son implémentation est réalisée par une classe
 * JDBC (UserDAOMariaDB) et Elle est utilisée par la couche service (UserService). Cette interface extends
 * CrudDAO<User, String> et extends TimestampedDAO<User> afin d'hériter des opérations de recherche par date de création
 * et par date de modification, ainsi que les opération CRUD génériques.
 *
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 * @since 25 mai 2026
 */
public interface UserDAO extends CrudDAO<User, String> {
    /**
     * Récupérer le User existant par son email donné dans la base de données
     * @param email le email du User
     * @return le User trouvé incluant toutes les valeurs généré par la base de données
     */
    User findByEmail(String email);

    /**
     * Vérifier si le email existe dans la base de données
     * @param email le email du User
     * @return true si le User existe sinon false
     */
//    boolean emailExists(String email);

    /**
     * Récupérer le User existant par son username dans la base de données
     * @param username le username du User
     * @return le User trouvé incluant toutes les valeurs généré par la base de données
     */
//    User findByUsername(String username);

    /**
     * Vérifier si le username existe dans la base données
     * @param username le username du User
     * @return true si le username existe sinon false
     */
//    boolean usernameExists(String username);

    /**
     * Récupérer le ou les User par leur prénom dans la base de données
     * @param firstName le prénom des User
     * @return une liste de User portant le prénom spécifié
     */
    List<User> findByFirstName(String firstName);

    /**
     * Récupérer le ou les User par leur nom dans la base de données
     * @param lastName le nom des User
     * @return une liste de User portant le nom spécifié
     */
    List<User> findByLastName(String lastName);

    /**
     * récupérer les User par leur nom complets (prénom, nom)
     * @param firstName le prénom du usder
     * @param lastName le nom du User
     * @return une liste de User portant le prénom et nom spécifiés
     */
    List<User> findByFullName(String firstName, String lastName);

    /**
     * Récupérer tous les User par leur statut de compte soit 'active' ou 'inactive'
     * @param accountStatus le statut de compte des User
     * @return une liste de tous les User avec le statut de compte spécifié
     */
//    List<User> findByAccountStatus(String accountStatus);
}
