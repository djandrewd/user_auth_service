package ua.goit.offline5.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Created hello applicatoin!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Removed hello applicatoin!");
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        LOGGER.info("Session created: " + se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        LOGGER.info("Session removed: " + se);
    }
}
