package edu.tp.a11.assassinscreedstore.persistence;


import edu.tp.a11.assassinscreedstore.model.Cart;
import edu.tp.a11.assassinscreedstore.repository.Database;
import java.math.BigDecimal;
import java.sql.*;
import java.util.UUID;


public class CartDAO_MariaDB
        extends AbstractCrudDAO<Cart, String>
            implements CartDAO {

    private static final String INSERT_SQL = "INSERT INTO cart " +
            "(cartId, userId, totalPrice) " +
            "VALUES (?, ?, ?)";

    private static final String UPDATE_SQL = "UPDATE cart SET " +
            "userId  = ?, totalPrice = ? " +
            "WHERE cartId = ?";

    private static final String FIND_CART_BY_USER_ID =
            "SELECT * FROM cart WHERE userId = ?";

    private static final String FIND_BY_TOTAL_PRICE =
            "SELECT * FROM cart WHERE totalPrice = ?";

    private static final String UPDATE_TOTAL =
            "UPDATE cart SET totalPrice = ? WHERE cartId = ?";

    private static final String SELECT_CART_ITEM_COUNT =
            "SELECT COUNT(*) FROM cart_items  WHERE cartId = ?";

    public CartDAO_MariaDB(Database database) {
        super(database);
    }

    @Override
    protected String getTableName() {
        return "cart";
    }

    @Override
    protected String getPrimaryKeyColumn() {
        return "cartId";
    }

    @Override
    protected String getPrimaryKeyValue(Cart entity) {
        return entity.getCartId();
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
    protected Cart mapRow(ResultSet rs) throws SQLException {
        Cart cart = new Cart();
        cart.setCartId(rs.getString("cartId"));
        cart.setUserId(rs.getString("userId"));
        cart.setTotalPrice(rs.getBigDecimal("totalPrice"));

        return cart;
    }

    @Override
    protected void fillInsertStatement(PreparedStatement ps, Cart entity) throws SQLException {
        String cartId = UUID.randomUUID().toString();
        entity.setCartId(cartId);

        ps.setString(1, entity.getCartId());
        ps.setString(2, entity.getUserId());
        ps.setBigDecimal(3, entity.getTotalPrice());
    }

    @Override
    protected void fillUpdateStatement(PreparedStatement ps, Cart entity) throws SQLException {
        ps.setString(1, entity.getUserId());
        ps.setBigDecimal(2, entity.getTotalPrice());
        ps.setString(3, entity.getCartId());
    }

    @Override
    public int updateCartItemCount(String id) {
        try (Connection conn = database.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_CART_ITEM_COUNT)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec updateCartItemCount", e);
        }
        return 0;
    }


    @Override
    public Cart findCartByUserId(String id) {
        try(Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_CART_BY_USER_ID)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec findByUserId", e);
        }
        return null;
    }

    @Override
    public void updateTotalPrice(String id,  BigDecimal totalPrice) {
        try(Connection conn = Database.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_TOTAL)) {

            ps.setString(1, id);
            ps.setBigDecimal(2, totalPrice);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur avec updateTotalPrice", e);
        }

    }


}