package core.dao;

import core.model.Order;
import core.model.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);
    
    List<Order> getOrdersHistory(User user);
}
