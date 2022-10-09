package api;

import Dao.UserDao;
import enity.user;
import util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @title: LoginServlet
 * @Author Xu
 * @Date: 9/10/2022 下午 3:08
 * @Version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");


        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        if ( userName == null || password == null || "".equals(userName) || "".equals(password)) {
            String html = "<h3>登录失败! 用户名或者密码为空! </h3>";
            resp.getWriter().write(html);
            return;
        }
        UserDao userDao = new UserDao();
        user user = userDao.selectByName(userName);
        if(user == null){
            String html = "<h3>登录失败! 用户名或者密码错误! </h3>";
            resp.getWriter().write(html);
            return;
        }
        if(!user.getPassword().equals(password)){
            String html = "<h3>登录失败! 用户名或者密码错误! </h3>";
            resp.getWriter().write(html);
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect("blog_list.html");
    }
}