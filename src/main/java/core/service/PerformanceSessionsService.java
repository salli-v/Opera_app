package core.service;

import core.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;

public interface PerformanceSessionsService {
    List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date);
    
    PerformanceSession get(Long id);
    
    PerformanceSession add(PerformanceSession session);
}
