package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Connection connection = null;
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        connection = Util.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATETABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        connection = Util.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(DELETETABLE);

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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        connection = Util.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(DELETEUSER)){
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

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
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        connection = Util.getConnection();
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate(CLEARTABLE);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
