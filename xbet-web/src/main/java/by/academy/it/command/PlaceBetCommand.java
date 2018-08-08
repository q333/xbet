package by.academy.it.command;

import by.academy.it.entity.Match;
import by.academy.it.service.MatchService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This command retrieves a match id, finds match, puts it in the session and sends to user 'place bet' page
 *
 */
public class PlaceBetCommand extends Command {

    private static final Logger logger = LogManager.getLogger(PlaceBetCommand.class);
    private MatchService matchService = MatchService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchId = request.getParameter(MATCH_ID);
        if (isValidString(matchId)) {
            try {
                int id = Integer.parseInt(matchId);
                logger.info("parameter matchId - [" + matchId + "]");
                Match match = matchService.getMatchById(id);
                logger.info("match has been retrieved - [" + match.getId() + "]");
                request.getSession().setAttribute(MATCH, match);

                response.sendRedirect(request.getContextPath() + MAIN_BET);

            } catch (Exception e) {
                logger.error("An exception occurred during get match operation", e);
                e.printStackTrace();
                request.getSession().setAttribute(ERROR_MESSAGE, MATCH_EXCEPTION);

                response.sendRedirect(request.getContextPath() + ERROR);
            }
        } else {
            logger.error("Match id is not valid");
            request.getSession().setAttribute(ERROR_MESSAGE, MATCH_ID_ERROR);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
