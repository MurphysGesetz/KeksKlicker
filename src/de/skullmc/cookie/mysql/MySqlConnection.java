package de.skullmc.cookie.mysql;

import java.sql.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySqlConnection {
    private final String ip, port, database, username, password;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private Connection connection;

    public MySqlConnection(final String ip, final String port, final String database, final String username, final String password) {
        this.ip = ip;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public boolean isConnected() {
        return connection != null;
    }

    public void connect() {
        executorService.execute(() -> {
            if (isConnected()) return;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                System.out.println("It was successfully connected to the database");
                update("CREATE TABLE IF NOT EXISTS cookies(UUID char(36) UNIQUE, NAME char(16), COOKIES bigint);");
                update("CREATE TABLE IF NOT EXISTS achievments(UUID varchar(36) UNIQUE, ACHIEVMENTS TINYINT);");
                update("CREATE TABLE IF NOT EXISTS upgrades(UUID varchar(36) UNIQUE, HELPER TINYINT, GRANDMA TINYINT," +
                        " FINGER TINYINT, CHICKEN TINYINT, MINIGUN TINYINT, CODER TINYINT, PLANTATION TINYINT, TURBINE TINYINT," +
                        " HUSTLER TINYINT, CHINA TINYINT, NUCLEAR TINYINT);");
            } catch (final SQLException exception) {
                exception.printStackTrace();
                System.out.println("It couldn't connected to the database");
            }
        });
    }

    public void disconnect() {
        executorService.execute(() -> {
            if (!isConnected()) return;
            try {
                connection.close();
                System.out.println("The connection to the database was successfully closed");
            } catch (final SQLException exception) {
                System.out.println("The connection to the database could not be closed");
            }
        });
    }

    public PreparedStatement getStatement(final String sql) throws SQLException {
        return !isConnected() ? null : connection.prepareStatement(sql);
    }

    private void update(final String query) {
        this.executorService.execute(() -> {
            try {
                final Statement statement = this.connection.createStatement();
                statement.execute(query);
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
