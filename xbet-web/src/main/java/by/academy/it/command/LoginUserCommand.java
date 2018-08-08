package by.academy.it.command;


import by.academy.it.entity.User;
import by.academy.it.service.ServiceException;
import by.academy.it.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command puts into session data about logged user
 *
 */
public class LoginUserCommand extends Command {

    private static final Logger logger = LogManager.getLogger(LoginUserCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        try {
            if (isValidString(password) && userService.isPasswordCorrectForLogin(login, password)) {
                User user = userService.findUserByLogin(login);
                if (user != null) {
                    user.setPassword(null);
                    request.getSession().setAttribute(USER, user);
                    logger.info("user has been logged in");

                    response.sendRedirect(request.getContextPath() + HOME);
                } else {
                    logger.error("user with login [" + login + "] doesn't exist");
                    request.getSession().setAttribute(ERROR_MESSAGE, LOGIN_FAILURE);

                    response.sendRedirect(request.getContextPath() + ERROR);
                }
            } else {
                logger.error("password is incorrect");
                request.setAttribute(MESSAGE, INCORRECT_PASSWORD);

                request.getRequestDispatcher(getReferrerPath(request)).forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error("An exception occurred during login action", e);
            e.printStackTrace();
            request.getSession().setAttribute(ERROR_MESSAGE, LOGIN_EXCEPTION);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
