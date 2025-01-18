package org.example;

import javax.xml.crypto.Data;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    DatabaseConnection connectionDB = new DatabaseConnection();
    Connection connection = null;
    PreparedStatement pStatement = null;
    CallableStatement cStatement = null;

    public void insert(String username, String password){
        String query = "INSERT INTO `demo_schema`.`account` " + "(`username`, `password`) " + "VALUES (?,?)";

        try{
            connection = connectionDB.getConnection();

            pStatement = connection.prepareStatement(query);
            pStatement.setString(1, username);
            pStatement.setString(2, password);

            int rowsAffected = pStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insert data successfully");
            } else {
                throw new SQLException("Insert failed for " + username);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void insertV2(String username, String password) {
        try {
            connection = connectionDB.getConnection();

            // Call the stored procedure
            cStatement = connection.prepareCall("{CALL proc_insert_user(?, ?, ?)}");
            cStatement.setString(1, username);
            cStatement.setString(2, password);

            // Register the output parameter for status
            cStatement.registerOutParameter(3, java.sql.Types.INTEGER);

            // Execute the stored procedure
            cStatement.execute();

            // Retrieve the value of the output parameter
            int status = cStatement.getInt(3);

            // Check the status
            if (status == 1) {
                System.out.println("Insert data successfully");
            } else {
                throw new SQLException("Insert failed for " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (cStatement != null) {
                    cStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
