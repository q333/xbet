package by.academy.it.dao.factory;

import by.academy.it.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * This factory creates and returns dao instances
 *
 */
public class DaoFactory {
    private static DaoFactory instance;
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(DaoFactory.class);

    private BetDao betDao;
    private MatchDao matchDao;
    private ResultDao resultDao;
    private RoleDao roleDao;
    private TeamDao teamDao;
    private UserDao userDao;

    /**
     * Prohibits creating instance of class outside the class
     */
    private DaoFactory() {
    }

    /**
     * Returns an instance of the DaoFactory;
     * @return DaoFactory
     */
    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    /**
     * Creates BetDao instance.
     * puts a connectionPool into the dao instance and return it
     *
     * @return BetDao instance
     */
    public BetDao getBetDao() {
        if (betDao == null) {
            betDao = new BetDaoImpl(connectionPool);
            logger.info("DaoFactory created a BetDao");
        }
        return betDao;
    }

    /**
     * Creates UserDao instance.
     * puts a connectionPool into the dao instance and return it
     *
     * @return UserDao instance
     */
    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoImpl(connectionPool);
            logger.info("DaoFactory created a UserDao");
        }
        return userDao;
    }

    /**
     * Creates RoleDao instance.
     * puts a connectionPool into the dao instance and return it
     *
     * @return RoleDao instance
     */
    public RoleDao getRoleDao() {
        if (roleDao == null) {
            roleDao = new RoleDaoImpl(connectionPool);
            logger.info("DaoFactory created a RoleDao");
        }
        return roleDao;
    }

    /**
     * Creates TeamDao instance.
     * puts a connectionPool into the dao instance and return it
     *
     * @return TeamDao instance
     */
    public TeamDao getTeamDao() {
        if (teamDao == null) {
            teamDao = new TeamDaoImpl(connectionPool);
            logger.info("DaoFactory created a TeamDao");
        }
        return teamDao;
    }

    /**
     * Creates MatchDao instance.
     * puts a connectionPool into the dao instance and return it
     *
     * @return MatchDao instance
     */
    public MatchDao getMatchDao() {
        if (matchDao == null) {
            matchDao = new MatchDaoImpl(connectionPool);
            logger.info("DaoFactory created a MatchDao");
        }
        return matchDao;
    }

    /**
     * Creates ResultDao instance.
     * puts a connectionPool into the dao instance and return it
     *
     * @return ResultDao instance
     */
    public ResultDao getResultDao() {
        if (resultDao == null) {
            resultDao = new ResultDaoImpl(connectionPool);
            logger.info("DaoFactory created a ResultDao");
        }
        return resultDao;
    }

}
