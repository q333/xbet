package by.academy.it.command;

import by.academy.it.entity.Bet;
import by.academy.it.entity.User;
import by.academy.it.service.BetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This command retrieves a list of the user's bets and sends it to 'bets page'
 *
 */
public class ShowBetsCommand extends Command {

    private static final Logger logger = LogManager.getLogger(ShowBetsCommand.class);
    private BetService betService = BetService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(USER);
        try {
            List<Bet> list = betService.getUsersBets(user.getId());
            request.getSession().setAttribute(BETS, list);
            logger.info("bets list is retrieved");

            request.getRequestDispatcher(PATH + BETS + JSP).forward(request, response);

        } catch (Exception e) {
            logger.error("An exception occurred during get bets list operation", e);
            e.printStackTrace();
            request.getSession().setAttribute(ERROR_MESSAGE, BETS_EXCEPTION);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
