package ua.goit.offline5.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "HelloServlet",
        loadOnStartup = 1,
        urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServlet.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException,
            IOException {
        HttpSession session = req.getSession(true);
        LOGGER.info("session:" + session.getId() + ":" + session.getCreationTime());
        String name = req.getParameter("name");
        resp.setStatus(HttpServletResponse.SC_OK);
        try (OutputStream stream = resp.getOutputStream()) {
            stream.write(("Hello " + name).getBytes());
            stream.flush();
        }
    }
}
