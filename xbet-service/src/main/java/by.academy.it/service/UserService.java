package by.academy.it.service;

import by.academy.it.dao.DAOException;
import by.academy.it.dao.UserDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class works with UserDao
 *
 */
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    private UserDao userDao = DaoFactory.getInstance().getUserDao();
    private static UserService instance;

    /**
     * Prohibits creating instance of class outside the class
     */
    private UserService() {
    }

    /**
     * Returns an instance of the UserService;
     * @return UserService
     */
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    /**
     * Checks if a user with such login exists in the database
     *
     * @param login
     * @return boolean
     * @throws ServiceException
     */
    public boolean isExistingLogin(String login) throws ServiceException {
        try {
            User user = userDao.findByLogin(login);
            if (user != null) {
                return true;
            }
        } catch (DAOException e) {
            logger.error("UserService cannot find a user by login", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot find a user by login");
        }
        return false;
    }

    /**
     * Checks if a user with such login and password exists in the database
     *
     * @param login
     * @param password
     * @return boolean
     * @throws ServiceException
     */
    public boolean isPasswordCorrectForLogin(String login, String password) throws ServiceException {
        try {
            User user = userDao.findByLogin(login);
            if (user != null && password.equals(user.getPassword())) {
                return true;
            }
        } catch (DAOException e) {
            logger.error("UserService cannot check password", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot check password");
        }
        return false;
    }

    /**
     * Creates a user through UserDao
     *
     * @param user
     * @throws ServiceException
     */
    public void createUser(User user) throws ServiceException {
        try {
            userDao.create(user);
        } catch (DAOException e) {
            logger.error("UserService cannot create a user", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot create a user");
        }
    }

    /**
     * Updates a user through UserDao
     *
     * @param login
     * @param amount
     * @return User
     * @throws ServiceException
     */
    public User updateUserBalance(String login, int amount) throws ServiceException {
        User user;
        try {
            user = userDao.findByLogin(login);
            if (user != null) {
                user.setBalance(user.getBalance() + amount);
                userDao.update(user);
            } else {
                logger.error("UserService cannot update user's balance");
                throw new ServiceException("UserService cannot update user's balance");
            }
        } catch (DAOException e) {
            logger.error("UserService cannot update a user", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot update a user");
        }
        return user;
    }

    /**
     * Deletes a user through UserDao
     *
     * @param user
     * @throws ServiceException
     */
    public void deleteUser(User user) throws ServiceException {
        try {
            userDao.delete(user);
        } catch (DAOException e) {
            logger.error("UserService cannot delete a user", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot delete a user");
        }
    }

    /**
     * Retrieves a user by login through UserDao
     *
     * @param login
     * @return User
     * @throws ServiceException
     */
    public User findUserByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDao.findByLogin(login);
        } catch (DAOException e) {
            logger.error("UserService cannot find a user", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot find a user");
        }
        return user;
    }

    /**
     * Retrieves a user by login through UserDao
     *
     * @param id
     * @return User
     * @throws ServiceException
     */
    public User findUserById(int id) throws ServiceException {
        User user;
        try {
            user = userDao.findById(id);
        } catch (DAOException e) {
            logger.error("UserService cannot find a user", e);
            e.printStackTrace();
            throw new ServiceException("UserService cannot find a user");
        }
        return user;
    }
}
