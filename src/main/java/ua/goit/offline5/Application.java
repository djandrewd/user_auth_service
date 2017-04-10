package ua.goit.offline5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ua.goit.offline5.servlets.HelloServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Main application entrance class.
 * <p>
 * Created by andreymi on 4/3/2017.
 */
public class Application implements WebApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final String SPRING_MAIN_CONFIG = "application-context.xml";

    public void onStartup(ServletContext servletContext) throws ServletException {
        LOGGER.info("Starting users auth services application.");
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation(SPRING_MAIN_CONFIG);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/*");
    }
}
