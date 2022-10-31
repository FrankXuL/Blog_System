package Test;

import Dao.UserDao;
import enity.user;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * UserDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 30, 2022</pre>
 */
public class UserDaoTest {

    @Before
    public void before() throws Exception {
        System.out.println("UserDao类测试开始");
    }

    @After
    public void after() throws Exception {
        System.out.println("UserDao类测试结束");
    }

    /**
     * Method: insert(user user)
     */
    @Test
    public void testInsert() throws Exception {
        user user = new user();
        user.setUsername("oasis");
        user.setPassword("123456");
        UserDao userDao = new UserDao();
        userDao.insert(user);
        user user1 = userDao.selectByName("oasis");
        Assert.assertEquals(user.getUsername(),user1.getUsername());
    }

    /**
     * Method: selectAll()
     */
    @Test
    public void testSelectAll() throws Exception {
        UserDao userDao = new UserDao();
        List<user> list = userDao.selectAll();
        for (user e: list) {
            System.out.println(e);
        }
    }

    /**
     * Method: selectById(int Id)
     */
    @Test
    public void testSelectById() throws Exception {
        UserDao userDao = new UserDao();
        user user = userDao.selectById(1);
        Assert.assertEquals(user.getUserId(),1);
    }

    /**
     * Method: delete(int blogId)
     */
    @Test
    public void testDelete() throws Exception {
        UserDao  userDao = new UserDao();
        userDao.delete(3);
    }

    /**
     * Method: selectByName(String username)
     */
    @Test
    public void testSelectByName() throws Exception {
        UserDao userDao = new UserDao();
        user user = userDao.selectByName("admin");
        Assert.assertEquals(user.getUserId(),1);
    }
} 
