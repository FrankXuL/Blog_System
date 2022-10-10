package api;

import Dao.BlogDao;
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
import java.sql.Timestamp;

/**
 * @title: BlogUpdateServlet
 * @Author Xu
 * @Date: 10/10/2022 上午 10:26
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@WebServlet("/update")
public class BlogUpdateServlet extends HttpServlet {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        // 监测用户登录状态
        user user = UserUtil.checkLoginStatus(req);
        if (user == null) {
            // 未登录的状态
            resp.setStatus(403);
            String html = "<h3>当前用户未登录</h3>";
            resp.getWriter().write(html);
            return;
        }
        String blogId = req.getParameter("blogId");
        if(blogId == null) {
            return;
        }
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectById(Integer.parseInt(blogId));
        if (blog == null) {
            resp.setStatus(403);
            String html = "<h3>当前博客不存在</h3>";
            resp.getWriter().write(html);
            return;
        }
        String jsonString = objectMapper.writeValueAsString(blog);
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        user user = UserUtil.checkLoginStatus(req);
        if (user == null) {
            String html = "<h3>未登录不能编辑博客</h3>";
            resp.getWriter().write(html);
            return;
        }
        String blogId = req.getParameter("blogId");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || "".equals(title) || content == null || "".equals(content)) {
            String html = "<h3>当前编辑的博客标题或者正文缺失!</h3>";
            resp.getWriter().write(html);
            return;
        }
        Blog blog = new Blog();
        blog.setBlogId(Integer.parseInt(blogId));
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        BlogDao blogDao = new BlogDao();
        blogDao.update(blog);
        resp.sendRedirect("blog_detail.html");
    }
}