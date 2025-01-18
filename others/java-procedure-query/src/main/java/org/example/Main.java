package org.example;
import utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String username = Utils.getString("Enter your username: ", "Username is requried");
        String password = Utils.getString("Enter your password: ", "Password is requried");

        System.out.println("Data: " + username + " " + password);

        Query query = new Query();
        query.insertV2(username, password);
    }
}