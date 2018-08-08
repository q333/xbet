package by.academy.it.dao;

import by.academy.it.entity.Match;
import by.academy.it.dao.factory.ConnectionPool;
import by.academy.it.dao.factory.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Works with a <code>Match</code> entity class and have access to the 'matches' database table
 *
 */
public class MatchDaoImpl implements MatchDao {
    private static final Logger logger = LogManager.getLogger(MatchDaoImpl.class);
    private static ConnectionPool pool;

    private static final String CREATE_QUERY = "INSERT INTO xbet.matches " +
            "(date, team1_id, team2_id, `1`, X, `2`, `1X`, `12`, `2X`) " +
    "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM xbet.matches WHERE id = ?";
    private static final String GET_UNPLAYED_MATCHES_QUERY = "SELECT * FROM xbet.matches WHERE id NOT IN " +
            "(SELECT m.id FROM  xbet.matches m JOIN xbet.results r ON m.id = r.matches_id) ORDER BY date";
    private static final String DELETE_QUERY = "DELETE FROM xbet.matches WHERE id = ?";

    public MatchDaoImpl(ConnectionPool connectionPool) {
        pool = connectionPool;
    }

    /**
     * Creates a new match entry in the database
     *
     * @param match
     * @throws DAOException
     */
    public void create(Match match) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(CREATE_QUERY);
            statement.setDate(1, match.getDate());
            statement.setInt(2, match.getTeam1_id());
            statement.setInt(3, match.getTeam2_id());
            statement.setDouble(4, match.getVictory1());
            statement.setDouble(5, match.getDraw());
            statement.setDouble( 6, match.getVictory2());
            statement.setDouble( 7, match.getVictory1OrDraw());
            statement.setDouble( 8, match.getVictory1Or2());
            statement.setDouble( 9, match.getVictory2OrDraw());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("MatchDao cannot create a match in DAO", e);
            e.printStackTrace();
            throw new DAOException("MatchDao cannot create a match");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retrieves a match entry from the database
     *
     * @param id
     * @return Match entity
     * @throws DAOException
     */
    public Match findById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Match match = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            set = statement.executeQuery();
            if (set.next()) {
                match = new Match();
                match.setId(set.getInt("id"));
                match.setDate(set.getDate("date"));
                match.setTeam1_id(set.getInt("team1_id"));
                match.setTeam2_id(set.getInt("team2_id"));
                match.setVictory1(set.getDouble("1"));
                match.setDraw(set.getDouble("X"));
                match.setVictory2(set.getDouble("2"));
                match.setVictory1OrDraw(set.getDouble("1X"));
                match.setVictory1Or2(set.getDouble("12"));
                match.setVictory2OrDraw(set.getDouble("2X"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("MatchDao find by id operation is failed", e);
            e.printStackTrace();
            throw new DAOException("MatchDao find by id operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return match;
    }

    /**
     * Retrieves a list of match entries from the database
     *
     * @return List<Match>
     * @throws DAOException
     */
    public List<Match> getUnplayedMatches() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        List<Match> list = new ArrayList<Match>();
        try {
            connection = pool.getConnection();
            statement = connection.createStatement();
            set = statement.executeQuery(GET_UNPLAYED_MATCHES_QUERY);
            Match match;
            while (set.next()) {
                match = new Match();
                match.setId(set.getInt("id"));
                match.setDate(set.getDate("date"));
                match.setTeam1_id(set.getInt("team1_id"));
                match.setTeam2_id(set.getInt("team2_id"));
                match.setVictory1(set.getDouble("1"));
                match.setDraw(set.getDouble("X"));
                match.setVictory2(set.getDouble("2"));
                match.setVictory1OrDraw(set.getDouble("1X"));
                match.setVictory1Or2(set.getDouble("12"));
                match.setVictory2OrDraw(set.getDouble("2X"));
                list.add(match);
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("MatchDao get matches operation is failed", e);
            e.printStackTrace();
            throw new DAOException("MatchDao get matches operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return list;
    }

    /**
     * Deletes a match entry from the database
     *
     * @param match
     * @throws DAOException
     */
    public void delete(Match match) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setInt(1, match.getId());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("MatchDao cannot delete a match in DAO", e);
            e.printStackTrace();
            throw new DAOException("MatchDao cannot delete a match");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}
