package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao dao = new UserDaoJDBCImpl();
    //UserDao dao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        dao.createUsersTable();
        //System.out.println("Апдейт на создание таблицы выполнил");
    }

    public void dropUsersTable(){
        dao.dropUsersTable();
        //System.out.println("Апдейт на удаление таблицы выполнил");
    }

    public void saveUser(String name, String lastName, byte age){
        dao.saveUser(name,lastName,age);
        //System.out.println("Апдейт на добавление юзера выполнил");
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        dao.removeUserById(id);
        //System.out.println("Апдейт на удаление юзера выполнил");
    }

    public List<User> getAllUsers() {
        //System.out.println("Запись всех юзеров в лист выполнил");
        return dao.getAllUsers();
    }

    public void cleanUsersTable() {
        dao.cleanUsersTable();
        //System.out.println("Апдейт на очистку таблицы выполнил");
    }
}
