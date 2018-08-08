package by.academy.it.dao;

import by.academy.it.entity.Result;

/**
 * Defines ResultDao methods. Extends Dao interface
 */
public interface ResultDao extends Dao {

    void create(Result result) throws DAOException;

    Result findByMatchId(int matchId) throws DAOException;

    void delete(Result result) throws DAOException;
}
