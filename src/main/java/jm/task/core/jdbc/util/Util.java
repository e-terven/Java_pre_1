package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.String;
import java.util.Properties;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "mysql33!";
    private static final String DIALECT = "org.hibernate.dialect.MySQL5Dialect";

    private static SessionFactory sessionFactory;

    static {
        loadDriver();
    }
    public static void loadDriver() {
        try {
            Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println(con.isClosed());
        } catch (SQLException e) {
            System.out.println("Error: Unable to Connect to Database.");
            e.printStackTrace();
        }
        return con;
    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                Configuration configuration = new Configuration();

                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, USER);
                properties.put(Environment.PASS, PASSWORD);
                properties.put(Environment.DIALECT, DIALECT);

                configuration.setProperties(properties)
                        .addAnnotatedClass(User.class);

                StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (HibernateException e) {
                e.printStackTrace();
                System.err.println("Initial SessionFactory creation failed.");
            }
        }
        return sessionFactory;
    }
}
