package core.dao;

import core.model.ShoppingCart;
import core.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);
    
    ShoppingCart getByUser(User user);
    
    void update(ShoppingCart shoppingCart);
}
