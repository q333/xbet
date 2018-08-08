package by.academy.it.dao;

import by.academy.it.entity.Bet;

import java.util.List;

/**
 * Defines BetDao methods. Extends Dao interface
 */
public interface BetDao extends Dao {

    void create(Bet bet) throws DAOException;

    List<Bet> findByUserId(int userId) throws DAOException;

    void delete(Bet bet) throws DAOException;

    List<Bet> findByMatchId(int id) throws DAOException;

    void update(Bet bet) throws DAOException;
}
