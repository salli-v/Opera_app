package core.dao.impl;

import core.dao.OrderDao;
import core.exception.DataProcessingException;
import core.model.Order;
import core.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;
    
    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot create order: " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<Order> getOrdersHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> getOrderHistoryQuery = session.createQuery("from Order o left join fetch "
                    + "o.tickets left join fetch o.user where o.user = :user", Order.class);
            getOrderHistoryQuery.setParameter("user", user);
            return getOrderHistoryQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot find orders of user: " + user, e);
        }
    }
}
