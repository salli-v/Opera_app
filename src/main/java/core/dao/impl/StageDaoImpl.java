package core.dao.impl;

import core.dao.CinemaHallDao;
import core.exception.DataProcessingException;
import core.model.Stage;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StageDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;
    
    @Autowired
    public StageDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Stage add(Stage stage) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(stage);
            transaction.commit();
            return stage;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert stage: " + stage, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public Optional<Stage> get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(Stage.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a stage by id: " + id, e);
        }
    }
    
    @Override
    public List<Stage> getAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Stage> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Stage.class);
            criteriaQuery.from(Stage.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all stages", e);
        }
    }
}
