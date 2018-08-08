package by.academy.it.dao;

import by.academy.it.entity.Match;

import java.util.List;

/**
 * Defines MatchDao methods. Extends Dao interface
 */
public interface MatchDao extends Dao {

    void create(Match match) throws DAOException;

    Match findById(int id) throws DAOException;

    List<Match> getUnplayedMatches() throws DAOException;

    void delete(Match match) throws DAOException;
}
