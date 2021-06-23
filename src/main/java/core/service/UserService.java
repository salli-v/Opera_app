package core.service;

import core.model.User;
import java.util.Optional;

public interface UserService {
    User add(User user);
    
    Optional<User> findByEmail(String email);
}
