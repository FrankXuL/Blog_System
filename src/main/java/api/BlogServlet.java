package api;

import Dao.BlogDao;
import enity.Blog;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        BlogDao blogDao = new BlogDao();
        List<Blog> blogs = blogDao.selectAll();
        String jsonString = objectMapper.writeValueAsString(blogs);
        resp.getWriter().write(jsonString);
    }
}