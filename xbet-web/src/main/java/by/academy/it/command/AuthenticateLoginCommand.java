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
 * This command authenticates login through ajax
 */
public class AuthenticateLoginCommand extends Command {

    private static final Logger logger = LogManager.getLogger(AuthenticateLoginCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("key");
        if (isValidString(login)) {
            logger.info("Authentication of login");
            try {
                if (userService.isExistingLogin(login)) {
                    logger.info("this login exists");
                    response.setHeader(RESULT, SUCCESS);
                } else {
                    logger.info("this login doesn't exists");
                    response.setHeader(RESULT, FAILURE);
                }
            } catch (ServiceException e) {
                logger.error("An exception occurred during login authentication", e);
                e.printStackTrace();
                response.setHeader(RESULT, null);
            }
        } else {
            logger.info("this login is not valid");
            response.setHeader(RESULT, FAILURE);
        }
        request.getRequestDispatcher(getReferrerPath(request)).forward(request, response);
    }

}
