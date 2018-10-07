import edu.zju.cst.myproject.dao.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.zju.cst.myproject.model.User;
import edu.zju.cst.myproject.util.EntitiesHelper;
import edu.zju.cst.myproject.util.HibernateUtil;

public class UserDaoTest {

    private IUserDao userDao;
    private Session session = null;

    @Before
    public void setUp() throws Exception {
        userDao = new UserDao();

        session = HibernateUtil.openSession();
        session.beginTransaction();
        session.save(new User("admin", "Ningbo"));
        session.getTransaction().commit();
    }

    @Test
    public void testGetUserByName() throws Exception {

        User tu = userDao.getUserByName("admin");
        EntitiesHelper.assertUser(tu);
    }

    @Test
    public void testAdd() throws Exception {

        User user = new User("user1", "Hangzhou");
        userDao.add(user);
        Assert.assertTrue("user id is " + user.getId(), user.getId() > 0);

        User tu = userDao.getUserByName("user1");
        EntitiesHelper.assertUser(tu, user);
    }

    @After
    public void tearDown() throws Exception {

        session.beginTransaction();
//		Query query = session.createQuery("from user");
//		session.delete(query);

//		List<User> userList = session.get
        session.createQuery(" delete from User").executeUpdate();
        session.getTransaction().commit();
        HibernateUtil.close(session);
    }
}
