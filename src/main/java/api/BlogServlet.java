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
import java.util.List;

/**
 * @title: BlogServlet
 * @Author Xu
 * @Date: 8/10/2022 上午 9:58
 * @Version 1.0
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
    static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");

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
        BlogDao blogDao = new BlogDao();
        if (blogId != null) {
            Blog blog = blogDao.selectById(Integer.parseInt(blogId));
            String jsonString = objectMapper.writeValueAsString(blog);
            resp.getWriter().write(jsonString);
        } else {
            List<Blog> blogs = blogDao.selectAll();
            String jsonString = objectMapper.writeValueAsString(blogs);
            resp.getWriter().write(jsonString);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        user user = UserUtil.checkLoginStatus(req);
        if (user == null) {
            String html = "<h3>未登录不能发布博客</h3>";
            resp.getWriter().write(html);
            return;
        }
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        if (title == null || "".equals(title) || content == null || "".equals(content)) {
            String html = "<h3>当前提交的博客标题或者正文缺失!</h3>";
            resp.getWriter().write(html);
            return;
        }
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setUserId(user.getUserId());
        blog.setPostTime(new Timestamp(System.currentTimeMillis()));
        BlogDao blogDao = new BlogDao();
        blogDao.insert(blog);
        resp.sendRedirect("blog_list.html");
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        user user = UserUtil.checkLoginStatus(req);
        if (user == null) {
            String html = "<h3>当前尚未登录, 不能删除</h3>";
            resp.getWriter().write(html);
            return;
        }
        String blogId = req.getParameter("blogId");
        if (blogId == null || "".equals(blogId)) {
            String html = "<h3>blogId 参数缺失!</h3>";
            resp.getWriter().write(html);
            return;
        }
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectById(Integer.parseInt(blogId));
        if (blog == null) {
            String html = "<h3>要删除的博客不存在!</h3>";
            resp.getWriter().write(html);
            return;
        }
        if (blog.getUserId() != user.getUserId()) {
            String html = "<h3>无法删除其他人的博客!</h3>";
            resp.getWriter().write(html);
            return;
        }
        blogDao.delete(Integer.parseInt(blogId));
        resp.setStatus(200);
        String html = "<h3>删除成功</h3>";
        resp.getWriter().write(html);
    }
}