package ua.goit.offline5.services.dao;

import ua.goit.offline5.model.User;

import java.util.Optional;

/**
 * DAO for users CRUD operations.
 * <p>
 * Created by andreymi on 4/3/2017.
 */
public interface UsersDao {

    User createUser(String username, String password);
    Optional<User> loadUser(String username);
    Optional<User> removeUser(String username);
}
