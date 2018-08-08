package by.academy.it.listener;

import by.academy.it.dao.factory.ConnectionPool;
import by.academy.it.dao.factory.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 */
public class ApplicationContextListener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(ApplicationContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Start of the application");
        ConnectionPool.getInstance().init();
        logger.info("Connection Pool has been initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Shutdown of the  application");
        try {
            ConnectionPool.getInstance().shutdownConnectionPool();
        } catch (ConnectionPoolException e) {
            logger.info("Connection Pool hasn't been closed");
            e.printStackTrace();
        }
        logger.info("Connection Pool has been closed");
    }
}
