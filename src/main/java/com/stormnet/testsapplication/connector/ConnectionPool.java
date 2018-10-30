package com.stormnet.testsapplication.connector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

public class ConnectionPool {

    private static final Log LOG = LogFactory.getLog(ConnectionPool.class);
    private static final int POOL_SIZE = 10;


    /* Class fields */
    private final BlockingQueue<Connection> pool;

    /* Constructor */
    private ConnectionPool() {
        pool = new ArrayBlockingQueue<>(POOL_SIZE);
        MySqlConnector mySqlConnector = MySqlConnector.getInstance();
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection connection= mySqlConnector.connect();
            pool.add(connection);
        }

    }

    private static class ConnectionPoolHolder {
        private final static ConnectionPool POOL = new ConnectionPool();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.POOL;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Connection result = null;
        try {
            result = pool.take();
        } catch (InterruptedException e) {
            LOG.error("InterruptedException", e);
        }
        return result;
    }

    /**
     * Return connection.
     *
     * @param connection the connection
     */
    public void returnConnection(Connection connection) {
        pool.add(connection);
    }

    /**
     * Close pool.
     */
    public void closePool() {
        for (Connection c : pool) {
            try {
                c.close();
            } catch (SQLException e) {
                LOG.error(e);
            }
        }
    }

}
