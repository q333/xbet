package by.academy.it.command;

import by.academy.it.entity.User;
import by.academy.it.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command tops up the user's balance
 *
 */
public class TopUpBalanceCommand extends Command {

    private static final Logger logger = LogManager.getLogger(TopUpBalanceCommand.class);
    private UserService userService = UserService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("amount");
        if (isValidString(param)) {
            try {
                int amount = Integer.parseInt(param);
                String login = ((User) request.getSession().getAttribute(USER)).getLogin();
                User user = userService.updateUserBalance(login, amount);
                user.setPassword(null);
                request.getSession().setAttribute(USER, user);
                logger.info("user's balance has been updated");

                response.sendRedirect(getReferrerURL(request));
            } catch (Exception e) {
                logger.error("An exception occurred during top up balance operation", e);
                e.printStackTrace();
                request.getSession().setAttribute(ERROR_MESSAGE, TOPUP_ERROR);

                response.sendRedirect(request.getContextPath() + ERROR);
            }
        } else {
            logger.error("Amount parameter is not valid");
            request.getSession().setAttribute(ERROR_MESSAGE, AMOUNT_ERROR);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
