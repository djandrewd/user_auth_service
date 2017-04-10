package ua.goit.offline5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.goit.offline5.services.dao.UsersDao;
import ua.goit.offline5.model.User;

import java.util.Optional;

/**
 * Service responsible for user CRUD operations.
 * <p>
 * Created by andreymi on 4/3/2017.
 */
@Component
public class UsersService {

    private final UsersDao dao;

    @Autowired
    public UsersService(UsersDao dao) {
        this.dao = dao;
    }

    public User createUser(String username, String password) {
        return dao.createUser(username, password);
    }

    public Optional<User> loadUser(String username) {
        return dao.loadUser(username);
    }

    public Optional<User> removeUser(String username) {
        return dao.removeUser(username);
    }
}
