package edu.tp.a11.assassinscreedstore.model;

/**
 *
 * @author zacch
 * @since 22 mai 2026
 */
public class User {

    // Attributs
    private String userId;
    private String email;
    private String firstName;
    private String lastName;
    private String passwordHash;

    // Constructeurs
    //Necessaire pour le DAO
    public User() {}

    //Business constructeur: utiliser pour enregistrer un user
    public User(String firstName, String lastName, String email, String passwordHash) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
    }

    // Methodes

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return String.format("User{userId= %s, email= %s, firstName= %s, lastName= %s, passwordHash= %s}",
                userId, email, firstName, lastName,  passwordHash);
    }

}

