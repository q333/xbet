package by.academy.it.service;


import by.academy.it.dao.BetDao;
import by.academy.it.dao.DAOException;
import by.academy.it.dao.MatchDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Bet;
import by.academy.it.entity.Match;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class works with BetDao and MatchDao
 *
 */
public class BetService {

    private static final Logger logger = LogManager.getLogger(BetService.class);
    private BetDao betDao = DaoFactory.getInstance().getBetDao();
    private MatchDao matchDao = DaoFactory.getInstance().getMatchDao();
    private static BetService instance;

    /**
     * Prohibits creating instance of class outside the class
     */
    private BetService() {
    }

    /**
     * Returns an instance of the BetService;
     * @return BetService
     */
    public static BetService getInstance() {
        if (instance == null) {
            instance = new BetService();
        }
        return instance;
    }

    /**
     * Creates a bet through BetDao
     *
     * @param bet
     * @throws ServiceException
     */
    public void createBet(Bet bet) throws ServiceException {
        try {
            betDao.create(bet);
        } catch (DAOException e) {
            logger.error("BetService cannot create a bet", e);
            e.printStackTrace();
            throw new ServiceException("BetService cannot create a bet");
        }
    }

    /**
     * Updates a bet through BetDao
     *
     * @param bet
     * @throws ServiceException
     */
    public void updateBet(Bet bet) throws ServiceException {
        try {
            betDao.update(bet);
        } catch (DAOException e) {
            logger.error("BetService cannot update a bet", e);
            e.printStackTrace();
            throw new ServiceException("BetService cannot update a bet");
        }
    }

    /**
     * Retrieves a list of bet entities through BetDao
     *
     * @param userId
     * @return List<Bet>
     * @throws ServiceException
     */
    public List<Bet> getUsersBets(int userId) throws ServiceException {
        List<Bet> list;
        try {
            list = betDao.findByUserId(userId);
            if (!list.isEmpty()) {
                for (Bet bet : list) {
                    Match match = matchDao.findById(bet.getMatch_id());
                    MatchService.getInstance().setTeams(match);
                    bet.setMatch(match);
                }
            }
        } catch (DAOException e) {
            logger.error("BetService cannot get a bets list", e);
            e.printStackTrace();
            throw new ServiceException("BetService cannot get a bets list");
        }
        return list;
    }

    /**
     * Retrieves a list of bet entities through BetDao
     *
     * @param matchId
     * @return List<Bet>
     * @throws ServiceException
     */
    public List<Bet> getMatchBets(int matchId) throws ServiceException {
        List<Bet> list;
        try {
            list = betDao.findByMatchId(matchId);
            if (!list.isEmpty()) {
                for (Bet bet : list) {
                    Match match = matchDao.findById(bet.getMatch_id());
                    MatchService.getInstance().setTeams(match);
                    bet.setMatch(match);
                }
            }
        } catch (DAOException e) {
            logger.error("BetService cannot get a bets list", e);
            e.printStackTrace();
            throw new ServiceException("BetService cannot get a bets list");
        }
        return list;
    }

}
