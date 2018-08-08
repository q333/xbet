package by.academy.it.service;

import by.academy.it.dao.DAOException;
import by.academy.it.dao.MatchDao;
import by.academy.it.dao.TeamDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Match;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * This class works with MatchDao and TeamDao
 *
 */
public class MatchService {

    private static final Logger logger = LogManager.getLogger(MatchService.class);
    private MatchDao matchDao = DaoFactory.getInstance().getMatchDao();
    private TeamDao teamDao = DaoFactory.getInstance().getTeamDao();
    private static MatchService instance;

    /**
     * Prohibits creating instance of class outside the class
     */
    private MatchService() {
    }

    /**
     * Returns an instance of the MatchService;
     * @return MatchService
     */
    public static MatchService getInstance() {
        if (instance == null) {
            instance = new MatchService();
        }
        return instance;
    }

    /**
     * Retrieves a list of match entities through MatchDao
     *
     * @return List<Match>
     * @throws ServiceException
     */
    public List<Match> getUnplayedMatches() throws ServiceException {
        List<Match> list;
        try {
            list = matchDao.getUnplayedMatches();
            if (!list.isEmpty()) {
                for (Match match : list) {
                    setTeams(match);
                }
            } else {
                logger.error("MatchService matches list is null");
                throw new ServiceException("MatchService matches list is null");
            }
        } catch (DAOException e) {
            logger.error("MatchService cannot get a matches list", e);
            e.printStackTrace();
            throw new ServiceException("MatchService cannot get a matches list");
        }
        return list;
    }

    /**
     * Retrieves a match entry through MatchDao
     *
     * @param id
     * @return Match
     * @throws ServiceException
     */
    public Match getMatchById(int id) throws ServiceException {
        Match match;
        try {
            match = matchDao.findById(id);
            if (match != null) {
                setTeams(match);
            } else {
                logger.error("MatchService match is null");
                throw new ServiceException("MatchService match is null");
            }
        } catch (DAOException e) {
            logger.error("MatchService cannot get a match by id", e);
            e.printStackTrace();
            throw new ServiceException("MatchService cannot get a matchby id");
        }
        return match;
    }

    /**
     * Finds teams by team1_id and team2_id fields and sets them to team1 and team2 fields
     *
     * @param match
     * @throws DAOException
     */
    void setTeams(Match match) throws DAOException {
        match.setTeam1(teamDao.findById(match.getTeam1_id()));
        match.setTeam2(teamDao.findById(match.getTeam2_id()));
    }

}
