package core.dao.impl;

import core.dao.MovieSessionDao;
import core.exception.DataProcessingException;
import core.model.PerformanceSession;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PerformanceSessionsDaoImpl implements MovieSessionDao {
    private static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);
    private final SessionFactory sessionFactory;
    
    @Autowired
    public PerformanceSessionsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public PerformanceSession add(PerformanceSession performanceSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(performanceSession);
            transaction.commit();
            return performanceSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can't insert performance session" + performanceSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<PerformanceSession> findAvailableSessions(Long performanceId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<PerformanceSession> criteriaQuery = criteriaBuilder
                    .createQuery(PerformanceSession.class);
            Root<PerformanceSession> root = criteriaQuery.from(PerformanceSession.class);
            Predicate moviePredicate = criteriaBuilder
                    .equal(root.get("performance"), performanceId);
            Predicate datePredicate = criteriaBuilder
                    .between(root.get("showTime"), date.atStartOfDay(), date.atTime(END_OF_DAY));
            Predicate allConditions = criteriaBuilder.and(moviePredicate, datePredicate);
            criteriaQuery.select(root).where(allConditions);
            root.fetch("performance");
            root.fetch("stage");
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't get available sessions for performance with " + "id: " + performanceId
                            + " for date: " + date, e);
        }
    }
    
    @Override
    public Optional<PerformanceSession> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<PerformanceSession> getQuery = session.createQuery("from PerformanceSession ms "
                    + "left join fetch ms.performance left join fetch ms.stage "
                    + "where ms.id = :id", PerformanceSession.class);
            getQuery.setParameter("id", id);
            return getQuery.uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a performance session by id: " + id, e);
        }
    }
}
