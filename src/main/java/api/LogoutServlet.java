package api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @title: LogoutServlet
 * @Author Xu
 * @Date: 9/10/2022 下午 6:40
 * @Version 1.0
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf8");
        HttpSession session = req.getSession(false);
        if (session == null) {
            String html = "当前尚未登录!";
            resp.getWriter().write(html);
            return;
        }
        session.removeAttribute("user");
        resp.sendRedirect("login.html");
    }
}