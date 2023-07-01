package de.eincode.iostein.cases.utils;

import de.eincode.iostein.cases.CaseSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

    private String username = CaseSystem.getInstance().getConfig().getString("MySQL.username");
    private String password = CaseSystem.getInstance().getConfig().getString("MySQL.password");
    private String host = CaseSystem.getInstance().getConfig().getString("MySQL.host");
    private String database = CaseSystem.getInstance().getConfig().getString("MySQL.database");
    private int port = CaseSystem.getInstance().getConfig().getInt("MySQL.port");

    public Connection connection;

    public boolean connect() {
        try {
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password;
            connection = DriverManager.getConnection(url);
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }


    public void createTable(String tableName, String columns) {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columns + ")";
            statement.executeUpdate(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
