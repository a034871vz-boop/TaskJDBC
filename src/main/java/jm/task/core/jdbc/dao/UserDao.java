package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import java.util.List;

public interface UserDao {

    String CREATETABLE = "CREATE TABLE IF NOT EXISTS User (Id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(30), lastName VARCHAR(30), age TINYINT)";
    String DELETETABLE = "DROP TABLE IF EXISTS User";
    String CTREATEUSER = "INSERT INTO User (name, lastName, age) values (?, ?, ?)";
    String DELETEUSER = "DELETE FROM USER WHERE Id = ?";
    String ALLUSERS = "SELECT * FROM User";
    String CLEARTABLE = "TRUNCATE TABLE User";

    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
