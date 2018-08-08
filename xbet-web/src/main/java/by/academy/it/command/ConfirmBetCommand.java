package by.academy.it.command;

import by.academy.it.entity.Bet;
import by.academy.it.entity.User;
import by.academy.it.service.BetService;
import by.academy.it.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This command retrieves a match id, a bet and amount and creates a bet entry
 *
 */
public class ConfirmBetCommand extends Command {

    private static final Logger logger = LogManager.getLogger(ConfirmBetCommand.class);
    private UserService userService = UserService.getInstance();
    private BetService betService = BetService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdParam = request.getParameter(MATCH_ID);
        String betParam = request.getParameter(BET);
        String amountParam = request.getParameter(AMOUNT);
        if (isValidString(matchIdParam) && isValidString(betParam) && isValidString(amountParam)) {
            try {
                int matchId = Integer.parseInt(matchIdParam);
                int amount = Integer.parseInt(amountParam);
                String[] betParams = betParam.split("/");
                double betValue = Double.parseDouble(betParams[1]);
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute(USER);
                String login = user.getLogin();
                userService.updateUserBalance(login, -amount);
                user.setBalance(user.getBalance() - amount);
                session.setAttribute(USER, user);
                logger.info("user's balance has been updated");

                Bet bet = new Bet();
                bet.setUser_id(user.getId());
                bet.setMatch_id(matchId);
                bet.setBetResult(betParams[0]);
                bet.setBet(betValue);
                bet.setMoney(amount);
                bet.setStatus("active");
                betService.createBet(bet);
                logger.info("bet entry has been created");

                request.setAttribute(CONFIRM_MESSAGE, SUCCESS);
                request.getRequestDispatcher(getReferrerPath(request)).forward(request, response);

            } catch (Exception e) {
                logger.error("An exception occurred during create bet operation", e);
                e.printStackTrace();
                request.getSession().setAttribute(ERROR_MESSAGE, BET_ERROR);

                response.sendRedirect(request.getContextPath() + ERROR);
            }
        } else {
            logger.error("Bet parameters are not valid");
            request.getSession().setAttribute(ERROR_MESSAGE, BET_PARAM_ERROR);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
