package core.security;

import core.exception.AuthenticationException;
import core.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;
    
    User register(String email, String password);
}
