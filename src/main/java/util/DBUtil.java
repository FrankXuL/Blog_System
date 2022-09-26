package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * @title: DBUtil
 * @Author Xu
 * @Date: 2022/9/26 22:51
 * @Version 1.0
 */
public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/blogsystem?autoReconnect=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static volatile DataSource datasource = null;

    public static DataSource getDatasource(){
        if(datasource == null){
            synchronized (DBUtil.class){
                if (datasource == null){
                    datasource = new MysqlDataSource();
                    ((MysqlDataSource)datasource).setURL(URL);
                    ((MysqlDataSource)datasource).setUser(USERNAME);
                    ((MysqlDataSource)datasource).setPassword(PASSWORD);
                }
            }
        }
        return datasource;
    }

    public static Connection getConnection() throws SQLException {
        return  getDatasource().getConnection();
    }

    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}