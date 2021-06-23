package core.service.impl;

import core.dao.CinemaHallDao;
import core.model.Stage;
import core.service.StageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl implements StageService {
    private final CinemaHallDao cinemaHallDao;
    
    @Autowired
    public StageServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }
    
    @Override
    public Stage add(Stage stage) {
        return cinemaHallDao.add(stage);
    }
    
    @Override
    public Stage get(Long id) {
        return cinemaHallDao.get(id).get();
    }
    
    @Override
    public List<Stage> getAll() {
        return cinemaHallDao.getAll();
    }
}
