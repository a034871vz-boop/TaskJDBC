package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String DELETEUSER = "DELETE FROM USER WHERE id = :?";

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(CREATETABLE).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(DELETETABLE).executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        //session.createSQLQuery(CTREATEUSER).executeUpdate();
        session.save(new User(name,lastName,age));
        transaction.commit();
        session.close();

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(DELETEUSER).setParameter("?", id).executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.createSessionFactory().openSession();
        List<User> list = session.createQuery("From User").list();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(CLEARTABLE).executeUpdate();
        transaction.commit();
        session.close();

    }
}
