package core.service.impl;

import core.dao.MovieDao;
import core.model.Performance;
import core.service.PerformanceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final MovieDao movieDao;
    
    @Autowired
    public PerformanceServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }
    
    @Override
    public Performance add(Performance performance) {
        return movieDao.add(performance);
    }
    
    @Override
    public Performance get(Long id) {
        return movieDao.get(id).get();
    }
    
    @Override
    public List<Performance> getAll() {
        return movieDao.getAll();
    }
}
