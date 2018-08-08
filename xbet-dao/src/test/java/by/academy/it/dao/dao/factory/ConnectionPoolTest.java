package by.academy.it.dao.dao.factory;

import by.academy.it.dao.factory.ConnectionPool;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionPoolTest {

    @Test
    public void getConnection() throws Exception {
        assertNotNull(ConnectionPool.getInstance().getConnection());
    }

}