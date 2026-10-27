package edu.tp.a11.assassinscreedstore.persistence;
//todo

import edu.tp.a11.assassinscreedstore.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Jason Bussière
 * @version 1.0 - 8 juin 2026
 * @since 31 mai 2026
 */
public interface CartItemDAO extends CrudDAO<CartItem, String> {
    List<CartItem> findByCartId(String id);
    List<CartItem> findByGameId(String id);
    List<CartItem> findByPrice(BigDecimal price);
    CartItem findByCartIdAndGameId(String cartId, String gameId);
    List<CartItem> findAllbyCartId(String cartId);
    void deleteAllByCartId(String cartId);
    List<CartItem> findByCartIdWithGames(String cartId);
}
