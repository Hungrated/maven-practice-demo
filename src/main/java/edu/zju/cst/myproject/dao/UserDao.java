package edu.zju.cst.myproject.dao;

import edu.zju.cst.myproject.model.User;
import edu.zju.cst.myproject.util.*;
import org.hibernate.Session;

public class UserDao implements IUserDao {

    public void add(User user) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            session.beginTransaction();

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            // handle the exception
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.close(session);

        }
    }

    public User getUserByName(String userName) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.openSession();
            user = (User) session.createQuery("from User where Name = ?")
                    .setParameter(0, userName).uniqueResult();

        } catch (Exception e) {
            // handle the exception
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.close(session);
        }
        return user;
    }

    public void delete(User user) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            session.beginTransaction();

            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            // handle the exception
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.close(session);

        }
    }
}
