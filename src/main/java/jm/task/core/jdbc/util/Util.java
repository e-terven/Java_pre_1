package jm.task.core.jdbc.util;

import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.lang.String;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql33!";
    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    private static final Properties PROPERTIES = new Properties();

    static {
        loadDriver();
        //loadProperties();
    }
    public static void loadDriver() {
        try {
            Class.forName(DRIVER); // загрузка в память JVM драйвера для работы с БД

        } catch (ClassNotFoundException e) {
            //e.printStackTrace(); // тогда приложение продолжит
            throw new RuntimeException(e);
        }
    }

    public static void loadProperties () {
        try (InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String get (String key) {
        return PROPERTIES.getProperty(key);
    }

    public static Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
//            con = DriverManager.getConnection(
//                    Util.get(URL_KEY),
//                    Util.get(USER_KEY),
//                    Util.get(PASSWORD_KEY)
//            );
            System.out.println(con.isClosed());
        } catch (SQLException e) {  // database access error || method is called on a closed connection
            System.out.println("Error: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return con;
    }



}
