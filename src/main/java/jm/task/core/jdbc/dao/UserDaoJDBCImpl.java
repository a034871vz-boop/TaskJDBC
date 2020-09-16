package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATETABLE = "CREATE TABLE IF NOT EXISTS User (Id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            "name VARCHAR(30), lastName VARCHAR(30), age TINYINT)";
    private static final String DELETETABLE = "DROP TABLE IF EXISTS User";
    private static final String CTREATEUSER = "INSERT INTO User (name, lastName, age) values (?, ?, ?)";
    private static final String DELETEUSER = "DELETE FROM USER WHERE Id = ?";
    private static final String ALLUSERS = "SELECT * FROM User";
    private static final String CLEARTABLE = "TRUNCATE TABLE User";

    Connection connection = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATETABLE);
            //System.out.println("Апдейт на создание таблицы выполнил");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        connection = Util.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(DELETETABLE);
            //System.out.println("Апдейт на удаление таблицы выполнил");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        connection = Util.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(CTREATEUSER)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();

            //System.out.println("Апдейт на добавление юзера выполнил");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        connection = Util.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETEUSER)){
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            //System.out.println("Апдейт на удаление юзера выполнил");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        connection = Util.getConnection();
        List<User> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(ALLUSERS);
            while (resultSet.next()){
                users.add(new User(resultSet.getString("name"), resultSet.getString("lastname"),
                        resultSet.getByte("age")));
            }
            //System.out.println("Запись всех юзеров в лист выполнил");
        }catch (Exception e){
            e.printStackTrace();
        }
        users.forEach(m -> System.out.println(m.toString()));
        return users;
    }

    public void cleanUsersTable() {
        connection = Util.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(CLEARTABLE);
            //System.out.println("Апдейт на очистку таблицы выполнил");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
