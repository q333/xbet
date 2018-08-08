package by.academy.it.dao;

import by.academy.it.entity.Team;
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
public class TeamDaoImpl implements TeamDao {
    private static final Logger logger = LogManager.getLogger(TeamDaoImpl.class);
    private static ConnectionPool pool;

    private static final String CREATE_QUERY = "INSERT INTO xbet.teams (name) VALUES (?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM xbet.teams WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM xbet.teams WHERE name = ?";

    public TeamDaoImpl(ConnectionPool connectionPool) {
        pool = connectionPool;
    }

    /**
     * Creates a new team entry in the database
     *
     * @param team
     * @throws DAOException
     */
    public void create(Team team) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(CREATE_QUERY);
            statement.setString(1, team.getName());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("TeamDao cannot create a team in DAO", e);
            e.printStackTrace();
            throw new DAOException("TeamDao cannot create a team");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retrieves a team entry from the database
     *
     * @param id
     * @return Team entity
     * @throws DAOException
     */
    public Team findById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Team team = null;
        try{
            connection = pool.getConnection();
            statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            set = statement.executeQuery();
            if (set.next()) {
                team = new Team();
                team.setId(set.getInt("id"));
                team.setName(set.getString("name"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("TeamDao find by id operation is failed", e);
            e.printStackTrace();
            throw new DAOException("TeamDao find by login operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return team;
    }

    /**
     * Deletes a team entry from the database
     *
     * @param team
     * @throws DAOException
     */
    public void delete(Team team) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setString(1, team.getName());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("TeamDao cannot delete a role in DAO", e);
            e.printStackTrace();
            throw new DAOException("TeamDao cannot delete a role");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

}
