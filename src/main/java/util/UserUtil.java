package util;

import enity.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @title: UserUtil
 * @Author Xu
 * @Date: 2022/9/26 22:03
 * @Version 1.0
 */
@SuppressWarnings({"all"})
public class UserUtil {
    public static user checkLoginStatus(HttpServletRequest req) {
        // 此处判定当前是否处于登录状态~. 如果能够拿到 Session, 并且拿到 Session 里的 user 对象, 就认为是已经登录过的状态
        HttpSession session = req.getSession(false);
        if (session == null) {
            // 未登录的状态
            return null;
        }
        user user = (user) session.getAttribute("user");
        if (user == null) {
            // 未登录状态~
            return null;
        }
        return user;
    }
}