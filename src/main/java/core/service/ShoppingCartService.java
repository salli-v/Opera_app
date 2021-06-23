package core.service;

import core.model.PerformanceSession;
import core.model.ShoppingCart;
import core.model.User;

public interface ShoppingCartService {
    void addSession(PerformanceSession performanceSession, User user);
    
    ShoppingCart getByUser(User user);
    
    void registerNewShoppingCart(User user);
    
    void clearShoppingCart(ShoppingCart cart);
}
