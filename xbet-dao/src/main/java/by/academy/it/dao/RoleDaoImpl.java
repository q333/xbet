package by.academy.it.dao;

import by.academy.it.entity.Role;
import by.academy.it.dao.factory.ConnectionPool;
import by.academy.it.dao.factory.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Works with a <code>Role</code> entity class and have access to the 'roles' database table
 *
 */
public class RoleDaoImpl implements RoleDao {
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);
    private static ConnectionPool pool;

    private static final String CREATE_QUERY = "INSERT INTO xbet.roles (role) VALUES (?)";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM xbet.roles WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM xbet.roles WHERE role = ?";

    public RoleDaoImpl(ConnectionPool connectionPool) {
        pool = connectionPool;
    }

    /**
     * Creates a new role entry in the database
     *
     * @param role
     * @throws DAOException
     */
    public void create(Role role) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(CREATE_QUERY);
            statement.setString(1, role.getRole());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("RoleDao cannot create a role in DAO", e);
            e.printStackTrace();
            throw new DAOException("RoleDao cannot create a role");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    /**
     * Retrieves a role entry from the database
     *
     * @param id
     * @return Role entity
     * @throws DAOException
     */
    public Role findById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet set = null;
        Role role = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(GET_BY_ID_QUERY);
            statement.setInt(1, id);
            set = statement.executeQuery();
            if (set.next()) {
                role = new Role();
                role.setId(set.getInt("id"));
                role.setRole(set.getString("role"));
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("RoleDao find by login operation is failed", e);
            e.printStackTrace();
            throw new DAOException("RoleDao find by id operation is failed");
        } finally {
            closeResultSet(set);
            closeStatement(statement);
            closeConnection(connection);
        }
        return role;
    }

    /**
     * Deletes a role entry from the database
     *
     * @param role
     * @throws DAOException
     */
    public void delete(Role role) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = pool.getConnection();
            statement = connection.prepareStatement(DELETE_QUERY);
            statement.setString(1, role.getRole());
            statement.execute();
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("RoleDao cannot delete a role in DAO", e);
            e.printStackTrace();
            throw new DAOException("RoleDao cannot delete a role");
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }
}
