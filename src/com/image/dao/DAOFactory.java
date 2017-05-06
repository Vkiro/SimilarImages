package com.image.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO
 */
public class DAOFactory {
    public static final String URL = "jdbc:mysql://localhost:3306/images?autoReconnect=true&useSSL=false";
    private static ImageDAO imageDAO = new ImageDAO();
    private static UserDAO userDAO = new UserDAO();

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, "root", "root");
    }

    //TODO
    public static ImageDAO getImageDAO() {
        return imageDAO;
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }
}
