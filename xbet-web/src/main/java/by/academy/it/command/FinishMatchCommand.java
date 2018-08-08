package by.academy.it.command;

import by.academy.it.entity.Bet;
import by.academy.it.entity.Match;
import by.academy.it.entity.Result;
import by.academy.it.entity.User;
import by.academy.it.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * This command randomly finishes the match
 *
 */
public class FinishMatchCommand extends Command {

    private static final Logger logger = LogManager.getLogger(FinishMatchCommand.class);
    private BetService betService = BetService.getInstance();
    private ResultService resultService = ResultService.getInstance();
    private MatchService matchService = MatchService.getInstance();
    private UserService userService = UserService.getInstance();



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matchIdParam = request.getParameter(MATCH_ID);
        if (isValidString(matchIdParam)) {
            try {
                int matchId = Integer.parseInt(matchIdParam);
                Match match = matchService.getMatchById(matchId);

                Result result = new Result();
                result.setMatchId(matchId);
                ResultEntry resultEntry = new ResultEntry();
                result.setResult(resultEntry.resultSymbol);
                if (resultEntry.resultSymbol.equals(FIRST_WON)){
                    result.setWinnerId(match.getTeam1_id());
                    result.setLoserId(match.getTeam2_id());
                    result.setWinnerGoals(resultEntry.team1goals);
                    result.setLoserGoals(resultEntry.team2goals);
                } else {
                    result.setWinnerId(match.getTeam2_id());
                    result.setLoserId(match.getTeam1_id());
                    result.setWinnerGoals(resultEntry.team2goals);
                    result.setLoserGoals(resultEntry.team1goals);
                }
                resultService.createResult(result);
                logger.info("result has been ctreated - " + result);

                checkBets(matchId, result.getResult(), request);

                HttpSession session = request.getSession();
                User user = userService.findUserByLogin(((User) session.getAttribute(USER)).getLogin());
                user.setPassword(null);
                session.setAttribute(USER, user);

                response.sendRedirect(getReferrerURL(request));
            } catch (Exception e) {
                logger.error("An exception occurred during finish match operation", e);
                e.printStackTrace();
                request.getSession().setAttribute(ERROR_MESSAGE, FINISH_ERROR);

                response.sendRedirect(request.getContextPath() + ERROR);
            }
        } else {
            logger.error("Match id parameter is not valid");
            request.getSession().setAttribute(ERROR_MESSAGE, MATCH_ID_ERROR);

            response.sendRedirect(request.getContextPath() + ERROR);
        }
    }

    private void checkBets(int matchId, String result, HttpServletRequest request) throws ServiceException {
        List<Bet> list = betService.getMatchBets(matchId);
        if (!list.isEmpty()) {
            for (Bet bet : list) {
                for (char c : bet.getBetResult().toCharArray()) {
                    if (result.equals(String.valueOf(c))) {
                        bet.setStatus("won");
                        User user = userService.findUserById(bet.getUser_id());
                        userService.updateUserBalance(user.getLogin(), (int) (bet.getMoney() * bet.getBet()));
                        logger.info("user balance in the database has been updated");
                        break;
                    } else {
                        bet.setStatus("lost");
                    }
                }
                betService.updateBet(bet);
                logger.info("bet with id [" + bet.getId() + "] " + bet.getStatus());
            }

        }
    }

    private class ResultEntry {
        int team1goals;
        int team2goals;
        String resultSymbol;

        ResultEntry() {
            getRandomResult();
        }

        void getRandomResult() {
            Random random = new Random();
            team1goals = random.nextInt(6);
            team2goals = random.nextInt(6);

            if (team1goals > team2goals) {
                resultSymbol = FIRST_WON;
            } else if (team1goals < team2goals) {
                resultSymbol = SECOND_WON;
            } else {
                resultSymbol = DRAW;
            }
        }
    }

}
