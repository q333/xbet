package by.academy.it.service;

import by.academy.it.dao.BetDao;
import by.academy.it.dao.factory.DaoFactory;
import by.academy.it.entity.Bet;
import org.junit.Test;

import static org.junit.Assert.*;

public class BetServiceTest {

    private BetDao betDao = DaoFactory.getInstance().getBetDao();

    @Test
    public void createBet() throws Exception {
        Bet bet = new Bet();
        bet.setUser_id(1);
        bet.setMatch_id(5);
        bet.setBetResult("X");
        bet.setBet(1.1111);
        bet.setMoney(111);
        bet.setStatus("test");
        betDao.create(bet);
        assertNotNull(betDao.findByUserId(1));
    }

}