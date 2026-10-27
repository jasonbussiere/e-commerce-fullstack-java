package edu.tp.a11.assassinscreedstore.persistence;
//todo javacdoc

import edu.tp.a11.assassinscreedstore.model.CartItem;
import edu.tp.a11.assassinscreedstore.model.Videogame;
import edu.tp.a11.assassinscreedstore.repository.Database;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 *  @since 31 mai 2026
 */
public class CartItemDAO_MariaDB
        extends AbstractCrudDAO<CartItem, String>
            implements CartItemDAO {

    private static final String INSERT_SQL = "INSERT INTO cart_items " +
            "(cartItemId, cartId, gameId, platform, total) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE cart_items SET " +
            "cartId = ?, gameId = ?, platform = ?, total = ? " +
            "WHERE cartItemId = ?";

    private static final String FIND_BY_CART_ID =
            "SELECT * FROM cart_items WHERE cartId = ?";

    private static final String FIND_BY_GAME_ID =
            "SELECT * FROM cart_items WHERE gameId = ?";

    private static final String FIND_BY_PRICE =
            "SELECT * FROM cart_items WHERE total = ?";

    private static final String FIND_BY_CART_ID_AND_GAME_ID=
            "SELECT * FROM cart_items WHERE cartId = ? AND gameId = ?";

    private static final String FIND_ALL_BY_CART_ID =
            "SELECT * FROM cart_items WHERE cartId = ?";

    private static final String DELETE_ALL_BY_CART_ID =
            "DELETE FROM cart_items WHERE cartId = ?";

    private static final String FIND_BY_CART_ID_WITH_GAMES =
                    "SELECT ci.*, vg.* " +
                    "FROM cart_items ci " +
                    "JOIN videogames vg ON ci.gameId = vg.gameId " +
                    "WHERE ci.cartId = ?";

    private final VideogameDAO videogameDAO;

    public CartItemDAO_MariaDB(Database database, VideogameDAO videogameDAO) {
        super(database);
        this.videogameDAO = videogameDAO;
    }

    @Override
    protected String getTableName() {
        return "cart_items";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "cartItemId";
    }

    @Override
    protected String getPrimaryKeyValue(CartItem entity) {
        return entity.getCartItemId();
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
    protected CartItem mapRow(ResultSet rs) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(rs.getString("cartItemId"));
        cartItem.setCartId(rs.getString("cartId"));
        cartItem.setGameId(rs.getString("gameId"));
        cartItem.setPlatform(rs.getString("platform"));
        cartItem.setTotal(rs.getBigDecimal("total"));
        cartItem.setVideogame(null);

        return cartItem;
    }


    @Override
    protected void fillInsertStatement(PreparedStatement ps, CartItem entity) throws SQLException {
        String cartItemId = UUID.randomUUID().toString();
        entity.setCartItemId(cartItemId);

        ps.setString(1, entity.getCartItemId());
        ps.setString(2, entity.getCartId());
        ps.setString(3, entity.getGameId());
        ps.setString(4, entity.getPlatform());
        ps.setBigDecimal(5, entity.getTotal());
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement ps, CartItem entity) throws SQLException {
        ps.setString(1, entity.getCartId());
        ps.setString(2, entity.getGameId());
        ps.setString(3, entity.getPlatform());
        ps.setBigDecimal(4, entity.getTotal());
        ps.setString(5, entity.getCartItemId());

    }

    @Override
    public List<CartItem> findByCartId(String id) {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_CART_ID)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByCartId", e);
        }
        return cartItems;

    }

    @Override
    public List<CartItem> findByGameId(String id) {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_GAME_ID)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByGameId", e);
        }
        return cartItems;

    }

    @Override
    public List<CartItem> findByPrice(BigDecimal price) {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_PRICE)) {

            ps.setBigDecimal(1, price);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByPrice", e);
        }
        return cartItems;

    }

    @Override
    public CartItem findByCartIdAndGameId(String cartId, String gameId) {
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_CART_ID_AND_GAME_ID)) {

            ps.setString(1, cartId);
            ps.setString(2, gameId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByCartIdAndGameId", e);
        }
        return null;
    }

    @Override
    public List<CartItem> findAllbyCartId(String cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_ALL_BY_CART_ID)) {

            ps.setString(1, cartId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cartItems.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findAllByCartId", e);
        }
        return cartItems;
    }

    @Override
    public void deleteAllByCartId(String cartId) {
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_ALL_BY_CART_ID)) {

            ps.setString(1, cartId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec deleteAllByCartId", e);
        }
    }

    @Override
    public List<CartItem> findByCartIdWithGames(String cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try(Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_CART_ID_WITH_GAMES)) {

            ps.setString(1, cartId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CartItem item = mapRow(rs);
                    Videogame videogame = videogameDAO.mapRow(rs);
                    item.setVideogame(videogame);
                    cartItems.add(item);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByCartIdWithGames", e);
        }
        return cartItems;
    }
}


