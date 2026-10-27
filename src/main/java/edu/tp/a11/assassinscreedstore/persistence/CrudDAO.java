package edu.tp.a11.assassinscreedstore.persistence;


import java.util.List;

/** Interface générique DAO (Data Access Object) qui défini l`ensemble des méthodes
 * CRUD: Create, Read, Update, Delete pour tout les entités persistants dans l'application.
 *
 * <p> L'interface fournit le contract pour toutes les DAO interfaces du système. Le timestampedDAO
 * extends cette interface et hérite de toutes les opérations CRUD.</p>
 * @param <T> Le type entité géré par le DAO
 * @param <ID> Le type de la clé primaire de l'entité
 *
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 * @since 25 mai 2026
 */
public interface CrudDAO<T, ID> {
    /**
     * Récupérer une entité par sa clé primaire.
     * @param id  la clé primaire de l'entité
     * @return l'entité trouvé ou null si aucun entité existe avec le id donné
     */
    T findById(ID id);

    /**
     * Récupérer tout les entités du type donné de la base de données.
     * @return une liste avec tout les entités; ne peut pas être null
     */
    List<T> findAll();

    /**
     * Modifier une entité existant de la base de données.
     * @param entity l'entité a modifier
     * @return l'entité modifié ou null si la modification échoue
     */
    T update(T entity);

    /**
     * Supprime une entité existant par sa clé primaire
     * @param id la clé primaire de l'entité à supprimer
     * @return true si la suppression de l'entité est réussi; false si la suppression a échoué
     */
    boolean delete(ID id);

    /**
     * Créer une entité dans la base de données
     * @param entity l'entité à créer selon le type donné
     * @return l'entité créée, incluant les valeurs générées par la base de données (clé primaire);
     * ne peut pas être null
     */
    T create (T entity);
}
