package core.dao.impl;

import core.dao.ShoppingCartDao;
import core.exception.DataProcessingException;
import core.model.ShoppingCart;
import core.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;
    
    @Autowired
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot create shopping cart ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> getByUserQuery = session.createQuery(
                    "from ShoppingCart " + "sp left join fetch sp.tickets t "
                            + "where sp.user = :user", ShoppingCart.class);
            getByUserQuery.setParameter("user", user);
            return getByUserQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a shopping cart by user: " + user, e);
        }
    }
    
    @Override
    public void update(ShoppingCart shoppingCart) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot create shopping cart ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
