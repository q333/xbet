package by.academy.it.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command terminates users session, deletes user's data from the session
 *
 */
public class LogoutCommand extends Command {

    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(USER, null);
        logger.info("logout is successful");

        response.sendRedirect(getReferrerURL(request));
    }
}
