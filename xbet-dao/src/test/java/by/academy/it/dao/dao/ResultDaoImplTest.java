package by.academy.it.dao.dao;

import by.academy.it.dao.ResultDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Result;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResultDaoImplTest {

    private ResultDao resultDao = DaoFactory.getInstance().getResultDao();

    @Test
    public void create() throws Exception {
        Result result = new Result();
        result.setMatchId(10);
        result.setResult("test");
        result.setWinnerId(1);
        result.setLoserId(2);
        result.setWinnerGoals(22);
        result.setLoserGoals(11);
        resultDao.create(result);
        Result result1 = resultDao.findByMatchId(10);
        assertEquals("test", result1.getResult());
    }

    @Test
    public void findByMatchId() throws Exception {
        Result result = resultDao.findByMatchId(1);
        assertNotNull(result);
    }

    @Test
    public void delete() throws Exception {
        Result result = new Result();
        result.setMatchId(10);
        resultDao.delete(result);
    }

}