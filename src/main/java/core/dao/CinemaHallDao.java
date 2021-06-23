package core.dao;

import core.model.Stage;
import java.util.List;
import java.util.Optional;

public interface CinemaHallDao {
    Stage add(Stage stage);
    
    Optional<Stage> get(Long id);
    
    List<Stage> getAll();
}
