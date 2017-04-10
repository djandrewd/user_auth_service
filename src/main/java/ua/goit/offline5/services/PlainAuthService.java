package ua.goit.offline5.services;

import org.springframework.beans.factory.annotation.Autowired;
import ua.goit.offline5.services.dao.UsersDao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Auth service checking validity by string match
 * <p>
 * Created by andreymi on 4/3/2017.
 */
public class PlainAuthService implements AuthService {

    private final UsersDao usersDao;

    @Autowired
    public PlainAuthService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public boolean login(String name, String password) throws Exception {
        return usersDao.loadUser(name).map(u -> u.getPassword().equals(md5(password))).orElse(false);
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
