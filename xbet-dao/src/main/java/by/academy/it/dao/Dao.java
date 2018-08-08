package by.academy.it.dao;

import by.academy.it.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Root dao interface. Defines default methods
 */
public interface Dao {
    Logger logger = LogManager.getLogger(Dao.class);

    /*
    * Closes a connection
    *
    * @param connection
    */
    default void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close a connection");
                e.printStackTrace();
            }
        }
    }

    /*
     * Closes a statement
     *
     * @param statement
     */
    default void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Cannot close a statement");
                e.printStackTrace();
            }
        }
    }

    /*
     * Closes a resultSet
     *
     * @param resultSet
     */
    default void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Cannot close a resultSet");
                e.printStackTrace();
            }
        }
    }

}
