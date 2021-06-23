package core.dao;

import core.model.Performance;
import java.util.List;
import java.util.Optional;

public interface MovieDao {
    Performance add(Performance performance);
    
    Optional<Performance> get(Long id);
    
    List<Performance> getAll();
}
