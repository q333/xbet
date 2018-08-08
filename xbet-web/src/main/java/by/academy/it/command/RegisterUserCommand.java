package by.academy.it.command;

import by.academy.it.entity.User;
import by.academy.it.service.ServiceException;
import by.academy.it.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command creates a new user in the database and puts user's data in the session
 */
public class RegisterUserCommand extends Command {

    private static final Logger logger = LogManager.getLogger(RegisterUserCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        String email = request.getParameter(EMAIL);

        if (isValidString(login) && isValidString(password) && isValidString(firstName)
                && isValidString(lastName) && isValidString(email)) {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setBalance(0);
            user.setRole(2);
            try {
                if (!userService.isPasswordCorrectForLogin(login, password)) {
                    userService.createUser(user);
                    user = userService.findUserByLogin(login);
                }
            } catch (ServiceException e) {
                logger.error("An exception occurred during login validation", e);
                e.printStackTrace();
                request.getSession().setAttribute(ERROR_MESSAGE, LOGIN_EXCEPTION);

                response.sendRedirect(request.getContextPath() + ERROR);
                return;
            }
            user.setPassword(null);
            request.getSession().setAttribute(USER, user);
            logger.info("user has been registered");

            response.sendRedirect(request.getContextPath() + HOME);

        } else {
            logger.error("Registration data are not valid");
            request.getSession().setAttribute(ERROR_MESSAGE, REGISTRATION_ERROR);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
