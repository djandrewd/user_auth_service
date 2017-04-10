package ua.goit.offline5.services;

/**
 * Service responsible for authentication and authorization.
 * <p>
 * Created by andreymi on 4/3/2017.
 */
public interface AuthService {
    boolean login(String name, String password) throws Exception;
}
