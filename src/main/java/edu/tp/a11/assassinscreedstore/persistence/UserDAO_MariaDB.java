package edu.tp.a11.assassinscreedstore.persistence;


import edu.tp.a11.assassinscreedstore.model.User;
import edu.tp.a11.assassinscreedstore.repository.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//javadoc todo
public class UserDAO_MariaDB
        extends AbstractCrudDAO<User, String>
        implements UserDAO {

    //SQL CONSTANTS
    private static final String INSERT_SQL = "INSERT INTO users " +
            "(userId, firstName, lastName, email, passwordHash) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE users SET " +
            "firstName = ?, lastName = ?, email = ?, passwordHash = ? " +
            "WHERE userId = ?";

    private static final String FIND_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";

//    private static final String FIND_BY_USERNAME =
//            "SELECT * FROM users WHERE username = ?";

    private static final String FIND_BY_FIRST_NAME =
            "SELECT * FROM users WHERE firstName = ?";

    private static final String FIND_BY_LAST_NAME =
            "SELECT * FROM users WHERE lastName = ?";

    private static final String FIND_BY_FULL_NAME =
            "SELECT * FROM users WHERE CONCAT(firstName, ' ', lastName) = ?";

//    private static final String FIND_BY_ACCOUNT_STATUS =
//            "SELECT * FROM users WHERE accountStatus = ?";

    public UserDAO_MariaDB(Database database) {
        super(database);
    }

    //ABSTRACT CRUD IMPLEMENTATION
    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "userId";
    }

    @Override
    protected String getPrimaryKeyValue(User entity) {
        return entity.getUserId();
    }

    @Override
    protected String getInsertSQL() {
        return INSERT_SQL;
    }

    @Override
    protected String getUpdateSQL() {
        return UPDATE_SQL;
    }

    @Override
    protected User mapRow(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getString("userId"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setEmail(rs.getString("email"));
        user.setPasswordHash(rs.getString("passwordHash"));

        return user;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement ps, User entity) throws SQLException {
        String userId = UUID.randomUUID().toString();
        entity.setUserId(userId);

        ps.setString(1, entity.getUserId());
        ps.setString(2, entity.getFirstName());
        ps.setString(3, entity.getLastName());
        ps.setString(4, entity.getEmail());
        ps.setString(5, entity.getPasswordHash());
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement ps, User entity) throws SQLException {
        ps.setString(1, entity.getFirstName());
        ps.setString(2, entity.getLastName());
        ps.setString(3, entity.getEmail());
        ps.setString(4, entity.getPasswordHash());
        ps.setString(5, entity.getUserId());
    }

    //CONCRETE CRUD IMPLEMENTATION
    @Override
    public User findByEmail(String email) {
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_EMAIL)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByEmail",e);
        }
        return null;
    }

//    @Override
//    public boolean emailExists(String email) {
//        return findByEmail(email) != null;
//    }

//    @Override
//    public User findByUsername(String username) {
//        try (Connection  conn = Database.getInstance().getConnection();
//             PreparedStatement ps = conn.prepareStatement(FIND_BY_USERNAME)) {
//
//            ps.setString(1, username);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                if (rs.next()) {
//                    return mapRow(rs);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Erreur avec findByUsername", e);
//        }
//        return null;
//    }

//    @Override
//    public boolean usernameExists(String username) {
//        return findByUsername(username) != null;
//    }

    @Override
    public List<User> findByFirstName(String firstName) {
        List<User> users = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_FIRST_NAME)) {

            ps.setString(1, firstName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByFirstName", e);
        }
        return users;
    }

    @Override
    public List<User> findByLastName(String lastName) {
        List<User> users = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_LAST_NAME)) {

            ps.setString(1, lastName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByLastName", e);
        }
        return users;
    }

    @Override
    public List<User> findByFullName(String firstName, String lastName) {
        List<User> users = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_BY_FULL_NAME)) {

            ps.setString(1, firstName + " " + lastName);


            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByFullName", e);
        }
        return users;
    }

//    @Override
//    public List<User> findByAccountStatus(String accountStatus) {
//        List<User> users = new ArrayList<>();
//        try (Connection conn = Database.getInstance().getConnection();
//             PreparedStatement ps = conn.prepareStatement(FIND_BY_ACCOUNT_STATUS)) {
//
//            ps.setString(1, accountStatus);
//
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    users.add(mapRow(rs));
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Erreur avec findByAccountStatus", e);
//        }
//        return users;
//    }
}
