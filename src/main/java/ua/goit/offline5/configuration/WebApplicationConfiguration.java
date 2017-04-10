package ua.goit.offline5.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ua.goit.offline5.services.PlainAuthService;
import ua.goit.offline5.services.dao.UsersDao;
import ua.goit.offline5.services.dao.imp.InMemoryUsersDao;

/**
 * Spring web application configuration
 * <p>
 * Created by andreymi on 4/3/2017.
 */
@Configuration
@EnableWebMvc
public class WebApplicationConfiguration {

    @Bean(name = "users_dao")
    public InMemoryUsersDao usersDao() {
        return new InMemoryUsersDao();
    }

    @Bean(name = "auth_service")
    public PlainAuthService authService(UsersDao usersDao) {
        return new PlainAuthService(usersDao);
    }
}
