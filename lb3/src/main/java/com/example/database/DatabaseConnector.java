package com.example.database;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Named
@SessionScoped
public class DatabaseConnector implements Serializable {

    private static final String URL = "jdbc:postgresql://localhost:5430/studs";
    private static final String USER = "HELOIS_LOGIN";
    private static final String PASSWORD = "POSTRGESQL_PASS";

    public Connection connect() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");

        // Establish the connection
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

        return connection;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}