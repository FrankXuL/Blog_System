package Dao;

import enity.Blog;
import enity.user;

import java.sql.Timestamp;
import java.util.List;

/**
 * @title: Test
 * @Author Xu
 * @Date: 2022/9/26 21:33
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        // 1. 测试一下新增用户
        user user = new user();
        user.setUsername("oasis");
        user.setPassword("123456");
        UserDao dao = new UserDao();
        userDao.insert(user);
    }
}