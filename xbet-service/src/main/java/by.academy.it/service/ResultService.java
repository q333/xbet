package by.academy.it.service;

import by.academy.it.dao.DAOException;
import by.academy.it.dao.ResultDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class works with ResultDao
 *
 */
public class ResultService {

    private static final Logger logger = LogManager.getLogger(ResultService.class);
    private ResultDao resultDao = DaoFactory.getInstance().getResultDao();
    private static ResultService instance;

    /**
     * Prohibits creating instance of class outside the class
     */
    private ResultService() {
    }

    /**
     * Returns an instance of the ResultService;
     * @return ResultService
     */
    public static ResultService getInstance() {
        if (instance == null) {
            instance = new ResultService();
        }
        return instance;
    }

    /**
     * Creates a match result through ResultDao
     *
     * @param result
     * @throws ServiceException
     */
    public void createResult(Result result) throws ServiceException {
        try {
            resultDao.create(result);
        } catch (DAOException e) {
            logger.error("ResultService cannot create a result", e);
            e.printStackTrace();
            throw new ServiceException("ResultService cannot create a result");
        }
    }

}
