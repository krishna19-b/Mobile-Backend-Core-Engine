package com.mobilebackend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/Mobile_Backend";

    private static final String USER =
            "postgres";

    private static final String PASSWORD =
            "Krishna@19";


    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}