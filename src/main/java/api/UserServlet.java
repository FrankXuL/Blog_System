package api;

import Dao.BlogDao;
import Dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import enity.Blog;
import enity.user;
import util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @title: UserServlet
 * @Author Xu
 * @Date: 9/10/2022 下午 4:10
 * @Version 1.0
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        user user = UserUtil.checkLoginStatus(req);
        if (user == null) {
            String html = "您尚未登录!";
            resp.setContentType("text/html;charset=utf-8");
            resp.getWriter().write(html);
            return;
        }
        user.setArticleNumber(new BlogDao().selectArticles(user.getUserId()));
        String blogId = req.getParameter("blogId");
        if (blogId == null) {
           String jsonString = objectMapper.writeValueAsString(user);
           resp.getWriter().write(jsonString);
        }else{
            BlogDao blogDao = new BlogDao();
            Blog blog = blogDao.selectById(Integer.parseInt(blogId));
            if (blog == null) {
                String html = "指定的博客不存在!";
                resp.setContentType("text/html;charset=utf-8");
                resp.getWriter().write(html);
                return;
            }
            UserDao userDao = new UserDao();
            user author = userDao.selectById(blog.getUserId());
            author.setArticleNumber(new BlogDao().selectArticles(blog.getUserId()));
            author.setIsYourBlog(author.getUserId() == user.getUserId() ? 1 : 0);
            String jsonString = objectMapper.writeValueAsString(author);
            resp.getWriter().write(jsonString);
        }
    }
}