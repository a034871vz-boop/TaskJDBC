package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASS = "root";

    public static Connection getConnection() {
        regDriver();
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
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
