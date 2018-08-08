package by.academy.it.dao;

import by.academy.it.entity.Team;

/**
 * Defines TeamDao methods. Extends Dao interface
 */
public interface TeamDao extends Dao {

    void create(Team team) throws DAOException;

    Team findById(int id) throws DAOException;

    void delete(Team team) throws DAOException;
}
