package by.academy.it.dao.dao;

import by.academy.it.dao.MatchDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Match;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class MatchDaoImplTest {

    private MatchDao matchDao = DaoFactory.getInstance().getMatchDao();

    @Test
    public void create() throws Exception {
        Match match = new Match();
        Date date = Date.valueOf(LocalDate.now());
        match.setDate(date);
        match.setTeam1_id(1);
        match.setTeam2_id(2);
        match.setVictory1(0.1);
        match.setDraw(0.1);
        match.setVictory2(0.1);
        match.setVictory1OrDraw(0.1);
        match.setVictory1Or2(0.1);
        match.setVictory2OrDraw(0.1);
        matchDao.create(match);
        Match match1 = matchDao.findById(11);
        assertNotNull(match1.getDate());
    }

    @Test
    public void findById() throws Exception {
        Match match = matchDao.findById(1);
        assertNotNull(match);
    }

    @Test
    public void getMatches() throws Exception {
        List<Match> list = matchDao.getUnplayedMatches();
        assertNotNull(list);
    }

    @Test
    public void delete() throws Exception {
        Match match = new Match();
        match.setId(15);
        matchDao.delete(match);
    }

}