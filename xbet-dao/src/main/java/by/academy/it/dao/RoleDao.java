package by.academy.it.dao;

import by.academy.it.entity.Role;

/**
 * Defines RoleDao methods. Extends Dao interface
 */
public interface RoleDao extends Dao {

    void create(Role role) throws DAOException;

    Role findById(int id) throws DAOException;

    void delete(Role role) throws DAOException;
}
