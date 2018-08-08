package by.academy.it.dao;

import by.academy.it.entity.User;
import by.academy.it.dao.factory.ConnectionPool;
import by.academy.it.dao.factory.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Works with a <code>User</code> entity class and have access to the 'users' database table
 *
 */
public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static ConnectionPool pool;

    private static final String CREATE_QUERY = "INSERT INTO xbet.users " +
            "(login, password, first_name, last_name, email, balance, role_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE xbet.users SET balance = ? WHERE login = ?";
    private static final String GET_BY_LOGIN_QUERY = "SELECT * FROM xbet.users WHERE login = ?";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM xbet.users WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM xbet.users WHERE login = ?";

    public UserDaoImpl(ConnectionPool connectionPool) {
        pool = connectionPool;
    }

    /**
     * Creates a new user entry in the database
     *
     * @param user
     * @throws DAOException
     */
    public void create(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(CREATE_QUERY);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getBalance());
            statement.setInt(7, user.getRole());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("UserDao cannot create a user in DAO", e);
            e.printStackTrace();
            throw new DAOException("UserDao cannot create a user");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Updates a user's entry in the database
     *
     * @param user
     * @throws DAOException
     */
    public void update(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setInt(1, user.getBalance());
            statement.setString(2, user.getLogin());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("UserDao cannot update a user in DAO", e);
            e.printStackTrace();
            throw new DAOException("UserDao cannot update a user");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retrieves a user entry from the database
     *
     * @param login
     * @return User entity
     * @throws DAOException
     */
    public User findByLogin(String login) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(GET_BY_LOGIN_QUERY);
            statement.setString(1, login);
            set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setId(set.getInt("id"));
                user.setLogin(set.getString("login"));
                user.setPassword(set.getString("password"));
                user.setFirstName(set.getString("first_name"));
                user.setLastName(set.getString("last_name"));
                user.setEmail(set.getString("email"));
                user.setBalance(set.getInt("balance"));
                user.setRole(set.getInt("role_id"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("UserDao find by login operation is failed", e);
            e.printStackTrace();
            throw new DAOException("UserDao find by login operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return user;
    }

    /**
     * Retrieves a user entry from the database
     *
     * @param id
     * @return User entity
     * @throws DAOException
     */
    @Override
    public User findById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        User user = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setId(set.getInt("id"));
                user.setLogin(set.getString("login"));
                user.setPassword(set.getString("password"));
                user.setFirstName(set.getString("first_name"));
                user.setLastName(set.getString("last_name"));
                user.setEmail(set.getString("email"));
                user.setBalance(set.getInt("balance"));
                user.setRole(set.getInt("role_id"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("UserDao find by login operation is failed", e);
            e.printStackTrace();
            throw new DAOException("UserDao find by login operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return user;
    }

    /**
     * Deletes a user entry from the database
     *
     * @param user
     * @throws DAOException
     */
    public void delete(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setString(1, user.getLogin());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("UserDao cannot delete a user in DAO", e);
            e.printStackTrace();
            throw new DAOException("UserDao cannot delete a user");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }


}
