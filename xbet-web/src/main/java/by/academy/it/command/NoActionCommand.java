package by.academy.it.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command is used when the application doesn't have a command for received URL
 *
 */
public class NoActionCommand extends Command {

    private static final Logger logger = LogManager.getLogger(NoActionCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(ERROR_MESSAGE, NO_COMMAND);
        logger.info("redirect to error page");

        response.sendRedirect(request.getContextPath() + ERROR);
    }
}
