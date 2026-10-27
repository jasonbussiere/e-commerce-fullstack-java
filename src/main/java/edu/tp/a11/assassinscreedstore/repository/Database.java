package edu.tp.a11.assassinscreedstore.repository;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** Gestionnaire de la connexion à la base de données. Encapsule les données de la configuration JDBC
 * fournit la connexion à tous les DAO du système.
 *
 * <p>Cette classe est responsable pour le chargement du pilote JDBC et de fournir les informations
 * de connexion: URL, username et password. L'implémentation emploie le patron Singleton pour créer
 * une seule instance de Database partagée et retourne les objets du type Connection.</p>
 *
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 * @since 25 mai 2026
 */
public class Database {
    /**
     * l'instance de l'objet Singleton
     */
    private static Database instance;

    /**
     *
     */
    private Properties properties;

    /**
     * Champs pour les informations de connexion à la base de données
     */
    private String host;
    private String name;
    private String port;
    private String username;
    private String password;
    private String url;

    /**
     * Constructeur privé instancié une seule fois.
     */
    private Database() {
        this.properties = new Properties();
        loadConfig();
        extractConfig();
    }

    /**
     * Méthode de chargement de la configuration de la connexion.
     */
    private void loadConfig() {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
        if (input==null){
            throw new RuntimeException("config properties introuvable");
        }

        try {
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("impossible de charger config properties");
        }
    }

    /**
     * Méthode pour extraire les propriétés et les stocker dans les champs pour informations de connexion.
     */
    private void extractConfig() {
        this.host=properties.getProperty("db.host");
        this.name=properties.getProperty("db.name");
        this.port=properties.getProperty("db.port");
        this.username = properties.getProperty("db.username");
        this.password = properties.getProperty("db.password");
        this.url="jdbc:mariadb://" + host+":"+port+"/"+name+"?useSSL=false&serverTimezone=UTC";
    }

    /**
     * La méthode pour récupérer l'instance de l'objet Database
     * @return l'instance de Database si il existe, sinon crée l'instance Database
     */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * La méthode pour ouvrir une connexion avec la base de données.
     * @return une connexion si réussi sinon retourne null si la connexion a échoue
     */
    public Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(
                    url, username, password
            );
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}