package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.DriverManager;


public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("В","ЧЕТВЕРГ", (byte) 13);
        service.saveUser("ЧЕТВЕРТОГО","ЧИСЛА", (byte) 23);
        service.saveUser("В","ЧЕТЫРЕ", (byte) 33);
        service.saveUser("С ЧЕТВЕРТЬЮ","ЧАСА", (byte) 43);
        service.getAllUsers();
        service.cleanUsersTable();
        service.dropUsersTable();
    }


}
