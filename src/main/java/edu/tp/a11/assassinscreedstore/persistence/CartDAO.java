package edu.tp.a11.assassinscreedstore.persistence;



import edu.tp.a11.assassinscreedstore.model.Cart;
import edu.tp.a11.assassinscreedstore.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

//todo javadoc
/**
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 * @since 31 mai 2026
 *
 */
public interface CartDAO extends CrudDAO<Cart, String> {
    int updateCartItemCount(String id);
    Cart findCartByUserId(String id);
    void updateTotalPrice(String id, BigDecimal totalPrice);
}
