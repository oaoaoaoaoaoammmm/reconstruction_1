package org.example.repository;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPoolCache {
    private final PooledDataSource source;

    DBPoolCache(String driver, String url, String user, String password) {
        source = new PooledDataSource();
        source.setDriver(driver);
        source.setUrl(url);
        source.setUsername(user);
        source.setPassword(password);
        source.setPoolMaximumIdleConnections(5);
        source.setPoolMaximumActiveConnections(5);
    }

    public Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    public void putConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
