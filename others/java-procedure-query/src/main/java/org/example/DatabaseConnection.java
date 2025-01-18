package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import io.github.cdimascio.dotenv.Dotenv;
public class DatabaseConnection {

    public Connection getConnection() throws SQLException {
        try {
            Properties properties = loadEnv();
            String dbUrl = properties.getProperty("DB_URL");
            String dbUsername = properties.getProperty("DB_USER");
            String dbPassword = properties.getProperty("DB_PASSWORD");
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (Exception e) {
            throw new SQLException("Failed to connect to database");
        }
    }

    private  Properties loadEnv() {
        Dotenv dotenv = Dotenv.configure().load();
        Properties properties = new Properties();
        properties.setProperty("DB_URL", dotenv.get("DB_URL"));
        properties.setProperty("DB_USER", dotenv.get("DB_USER"));
        properties.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        return properties;
    }

}
