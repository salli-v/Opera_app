package core.service;

import core.model.Order;
import core.model.ShoppingCart;
import core.model.User;
import java.util.List;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);
    
    List<Order> getOrdersHistory(User user);
}

