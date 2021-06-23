package core.dao.impl;

import core.dao.MovieDao;
import core.exception.DataProcessingException;
import core.model.Performance;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceSaoImpl implements MovieDao {
    private final SessionFactory sessionFactory;
    
    @Autowired
    public PerformanceSaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Performance add(Performance performance) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(performance);
            transaction.commit();
            return performance;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert performance " + performance, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public Optional<Performance> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Performance.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a performance by id: " + id, e);
        }
    }
    
    @Override
    public List<Performance> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Performance> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Performance.class);
            criteriaQuery.from(Performance.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all performances", e);
        }
    }
}
