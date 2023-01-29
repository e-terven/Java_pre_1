package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql33!";

    static {
        loadDriver();
    }
    public static void loadDriver() {
        try {
            Class.forName(DRIVER); // загрузка в память JVM драйвера для работы с БД
            // https://youtu.be/U7Pm3Vi0aWU?t=351
        } catch (ClassNotFoundException e) {
            // e.printStackTrace(); - тогда приложение продолжит
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection() {
        Connection con = null;
        // Class <Driver> driverClass = Driver.class;
        // в старрых версиях - авторматически (как здесь) драйверы не находятся
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println(con.isClosed());
        } catch (SQLException e) {  // database access error || method is called on a closed connection
            System.out.println("Error: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return con;
    }



}
