package Test;

import Dao.BlogDao;
import enity.Blog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

/**
 * BlogDao Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10月 30, 2022</pre>
 */
public class BlogDaoTest {

    @Before
    public void before() throws Exception {
        System.out.println("BlogDao类测试开始");
    }

    @After
    public void after() throws Exception {
        System.out.println("BlogDao类测试结束");
    }

    /**
     * Method: insert(Blog blog)
     */
    @Test
    public void testInsert() throws Exception {
        BlogDao blogDao = new BlogDao();
        Blog blog = new Blog();
        blog.setTitle("test");
        blog.setContent("hello Blog");
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        blogDao.insert(blog);
    }

    /**
     * Method: selectAll()
     */
    @Test
    public void testSelectAll() throws Exception {
        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.selectAll();
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
    }

    /**
     * Method: selectById(int blogId)
     */
    @Test
    public void testSelectById() throws Exception {
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectById(1);
        System.out.println(blog);
    }

    /**
     * Method: delete(int blogId)
     */
    @Test
    public void testDelete() throws Exception {
        BlogDao blogDao = new BlogDao();
        blogDao.delete(1);
    }

    /**
     * Method: selectArticles(int userId)
     */
    @Test
    public void testSelectArticles() throws Exception {
        BlogDao blogDao = new BlogDao();
        int n = blogDao.selectArticles(1);
        System.out.println(n);
    }
} 
