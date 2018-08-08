package by.academy.it.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple Command class to show static pages
 *
 */
public class ForwardRequestCommand extends Command {

    private static final Logger logger = LogManager.getLogger(ForwardRequestCommand.class);
    private String page;

    public ForwardRequestCommand(String page) {
        this.page = page;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        logger.info("requested page is '" + page + "'");

        request.getRequestDispatcher(PATH + page + JSP).forward(request, response);
    }
}
