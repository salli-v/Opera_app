package core.dao;

import core.model.PerformanceSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionDao {
    List<PerformanceSession> findAvailableSessions(Long movieId, LocalDate date);
    
    Optional<PerformanceSession> get(Long id);
    
    PerformanceSession add(PerformanceSession session);
}
