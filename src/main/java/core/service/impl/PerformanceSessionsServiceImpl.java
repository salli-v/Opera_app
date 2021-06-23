package core.service.impl;

import core.dao.MovieSessionDao;
import core.model.PerformanceSession;
import core.service.PerformanceSessionsService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceSessionsServiceImpl implements PerformanceSessionsService {
    private final MovieSessionDao sessionDao;
    
    @Autowired
    public PerformanceSessionsServiceImpl(MovieSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }
    
    @Override
    public List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date) {
        return sessionDao.findAvailableSessions(movieId, date);
    }
    
    @Override
    public PerformanceSession get(Long id) {
        return sessionDao.get(id).get();
    }
    
    @Override
    public PerformanceSession add(PerformanceSession session) {
        return sessionDao.add(session);
    }
}
