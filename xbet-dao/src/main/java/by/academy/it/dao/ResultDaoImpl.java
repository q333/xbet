package by.academy.it.dao;

import by.academy.it.entity.Result;
import by.academy.it.dao.factory.ConnectionPool;
import by.academy.it.dao.factory.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Works with a <code>Result</code> entity class and have access to the 'results' database table
 *
 */
public class ResultDaoImpl implements ResultDao {
    private static final Logger logger = LogManager.getLogger(ResultDaoImpl.class);
    private static ConnectionPool pool;

    private static final String CREATE_QUERY = "INSERT INTO xbet.results " +
            "(matches_id, result, winner_id, loser_id, winner_goals, loser_goals) " +
            "VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM xbet.results WHERE matches_id = ?";
    private static final String DELETE_QUERY = "DELETE FROM xbet.results WHERE matches_id = ?";

    public ResultDaoImpl(ConnectionPool connectionPool) {
        pool = connectionPool;
    }

    /**
     * Creates a new result entry in the database
     *
     * @param result
     * @throws DAOException
     */
    public void create(Result result) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(CREATE_QUERY);
            statement.setInt(1, result.getMatchId());
            statement.setString(2, result.getResult());
            statement.setInt(3, result.getWinnerId());
            statement.setInt(4, result.getLoserId());
            statement.setInt(5, result.getWinnerGoals());
            statement.setInt( 6, result.getLoserGoals());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("ResultDao cannot create a result in DAO", e);
            e.printStackTrace();
            throw new DAOException("ResultDao cannot create a result");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retrieves a result entry from the database
     *
     * @param matchId
     * @return Result entity
     * @throws DAOException
     */
    public Result findByMatchId(int matchId) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Result result = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, matchId);
            set = statement.executeQuery();
            if (set.next()) {
                result = new Result();
                result.setId(set.getInt("id"));
                result.setMatchId(set.getInt("matches_id"));
                result.setResult(set.getString("result"));
                result.setWinnerId(set.getInt("winner_id"));
                result.setLoserId(set.getInt("loser_id"));
                result.setWinnerGoals(set.getInt("winner_goals"));
                result.setLoserGoals(set.getInt("loser_goals"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("ResultDao find by match id operation is failed", e);
            e.printStackTrace();
            throw new DAOException("ResultDao find by match id operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return result;
    }

    /**
     * Deletes a result entry from the database
     *
     * @param result
     * @throws DAOException
     */
    public void delete(Result result) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, result.getMatchId());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("ResultDao cannot delete a result in DAO", e);
            e.printStackTrace();
            throw new DAOException("ResultDao cannot delete a result");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}
