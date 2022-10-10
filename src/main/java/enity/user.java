package enity;

/**
 * @title: user
 * @Author Xu
 * @Date: 2022/9/26 23:04
 * @Version 1.0
 */
@SuppressWarnings({"all"})

public class user {
    private int userId;
    private String username;
    private String password;
    private int isYourBlog;
    private int articleNumber;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    @Override
    public String toString() {
        return "user{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getIsYourBlog() {
        return isYourBlog;
    }

    public void setIsYourBlog(int isYourBlog) {
        this.isYourBlog = isYourBlog;
    }
}