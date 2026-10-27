package edu.tp.a11.assassinscreedstore.service;


import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.persistence.UserDAO;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author zacch, jason
 */
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            System.out.println("User not found");
            return null;
        }
        if (!BCrypt.checkpw(password, user.getPasswordHash())) {
            System.out.println("Wrong password");
            return null;
        }
        return user;
    }


    public void register(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User ne peut etre null");
        }

        if (user.getEmail() == null) {
            throw new IllegalArgumentException("Email ne peut etre null");
        }

        User existing = userDAO.findByEmail(user.getEmail());
        if (existing != null) {
            throw new IllegalArgumentException("User existe déjà");
        }

        // encrypte le mot de passe
        String hashed = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());
        user.setPasswordHash(hashed);
        System.out.println(user);
        userDAO.create(user);
        userDAO.findByEmail(user.getEmail());
    }

    private void valideUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (user.getPasswordHash() == null || user.getPasswordHash().length() < 6) {
            throw new IllegalArgumentException("Mot de passe doit être au moin 6 caratères");
        }
    }
}
