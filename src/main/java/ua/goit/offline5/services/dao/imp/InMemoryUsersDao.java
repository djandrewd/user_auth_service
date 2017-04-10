package ua.goit.offline5.services.dao.imp;

import ua.goit.offline5.services.dao.UsersDao;
import ua.goit.offline5.model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of users dao.
 * <p>
 * Created by andreymi on 4/3/2017.
 */
public class InMemoryUsersDao implements UsersDao {

    private Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public User createUser(String username, String password) {
        return users.computeIfAbsent(username, k -> {
            User user = new User();
            user.setPassword(md5(password));
            user.setUsername(username);
            return user;
        });
    }

    @Override
    public Optional<User> loadUser(String username) {
        return Optional.ofNullable(users.get(username));
    }

    @Override
    public Optional<User> removeUser(String username) {
        return Optional.ofNullable(users.remove(username));
    }

    private String md5(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(value.getBytes());
            return new BigInteger(bytes).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
