package by.academy.it.command;

import by.academy.it.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command checks the user's balance through ajax
 *
 */
public class CheckBalanceCommand extends Command {

    private static final Logger logger = LogManager.getLogger(CheckBalanceCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter(KEY);
        if (isValidString(key)) {
            try {
                int amount = Integer.parseInt(key);
                User user = (User) request.getSession().getAttribute(USER);
                if (amount > 0 && amount <= user.getBalance()) {
                    logger.info("balance check - enough money");
                    response.setHeader(RESULT, SUCCESS);
                } else {
                    logger.info("balance check - not enough money");
                    response.setHeader(RESULT, NOT_ENOUGH);
                }
            } catch (RuntimeException e) {
                logger.error("An exception occurred during check the user's balance operation", e);
                e.printStackTrace();
                request.getSession().setAttribute(ERROR_MESSAGE, BALANCE_ERROR);

                response.sendRedirect(request.getContextPath() + ERROR);
                return;
            }
        } else {
            logger.error("balance check - not valid key");
            response.setHeader(RESULT, FAILURE);
        }
        request.getRequestDispatcher(getReferrerPath(request)).forward(request, response);
    }
}
