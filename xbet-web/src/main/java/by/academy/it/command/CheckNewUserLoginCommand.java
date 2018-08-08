package by.academy.it.command;

import by.academy.it.service.ServiceException;
import by.academy.it.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command validates user login during registration through ajax
 */
public class CheckNewUserLoginCommand extends Command {

    private static final Logger logger = LogManager.getLogger(CheckNewUserLoginCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("key");
        if (isValidString(login) && isValidLogin(login)) {
            response.setHeader(RESULT, SUCCESS);
        } else {
            response.setHeader(RESULT, FAILURE);
        }
        request.getRequestDispatcher(getReferrerPath(request)).forward(request, response);
    }

    private boolean isValidLogin(String login) {
        boolean result = false;
        if (isValidString(login)) {
            try {
                if (userService.isExistingLogin(login)) {
                    logger.info("this login already exists");
                } else {
                    logger.info("this login is free");
                    result = true;
                }
            } catch (ServiceException e) {
                logger.error("An exception occurred during login validation", e);
                e.printStackTrace();
            }
        } else {
            logger.info("this login is not valid");
        }
        return result;
    }
}
