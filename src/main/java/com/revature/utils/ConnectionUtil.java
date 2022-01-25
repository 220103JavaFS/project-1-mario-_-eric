package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(("org.postgresql.Driver"));
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://project-1.cigojrhsanom.us-west-1.rds.amazonaws.com:5432/project1";
        String username = "postgres"; //It is possible and preferable to hide this information in environment variables
        //System.out.println(System.getenv("SQLPassword"));
        String password = "password"; //Those are accessed by System.getenv("var-name");

        return DriverManager.getConnection(url, username, password);
    }
}
