package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASS = "root";

    public static Connection getConnection() {
        //regDriver();
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASS);
            //if (!connection.isClosed()) System.out.println("Успешное Подключение к бд");
            if (connection.isClosed()) System.out.println("Соединение с бд закрыто");
            return connection;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка в классе Util", e);
        }
    }
    private static void regDriver() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory createSessionFactory() {
        SessionFactory sessionFactory = null;

            try {
                Properties prop = new Properties();

                //prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                prop.setProperty("hibernate.connection.url", URL);
                prop.setProperty("hibernate.connection.username", LOGIN);
                prop.setProperty("hibernate.connection.password", PASS);

                prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");
                prop.setProperty("show_sql", "true");
                prop.setProperty("hbm2ddl.auto", "create");

                Configuration configuration = new Configuration().addProperties(prop).addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

                //sessionFactory = new Configuration()
                //        .addProperties(prop)
                //        .addAnnotatedClass(User.class)
                //        .buildSessionFactory();

            } catch (HibernateException e) {
                e.printStackTrace();
            }
            return sessionFactory;
    }

}
