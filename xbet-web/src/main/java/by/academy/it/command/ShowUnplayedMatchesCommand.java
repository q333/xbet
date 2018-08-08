package by.academy.it.command;

import by.academy.it.entity.Match;
import by.academy.it.service.MatchService;
import by.academy.it.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This command retrieves a list of unplayed matches and sends it to 'matches page'
 *
 */
public class ShowUnplayedMatchesCommand extends Command {

    private static final Logger logger = LogManager.getLogger(ShowUnplayedMatchesCommand.class);
    private MatchService matchService = MatchService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Match> list = matchService.getUnplayedMatches();
            logger.info("Matches have been found");
            request.setAttribute(MATCHES_LIST, list);

            request.getRequestDispatcher(PATH + MATCHES + JSP).forward(request, response);

        } catch (ServiceException e) {
            logger.error("An exception occurred during get unplayed matches operation", e);
            e.printStackTrace();
            request.getSession().setAttribute(ERROR_MESSAGE, MATCH_EXCEPTION);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }
}
