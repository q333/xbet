package by.academy.it.dao;

import by.academy.it.entity.User;

/**
 * Defines UserDao methods. Extends Dao interface
 */
public interface UserDao extends Dao {

    void create(User user) throws DAOException;

    void update(User user) throws DAOException;

    User findByLogin(String login) throws DAOException;

    void delete(User user) throws DAOException;

    User findById(int id) throws DAOException;
}
