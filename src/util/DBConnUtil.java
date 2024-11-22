package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    private static Connection connection;

    public static Connection getConnection(String propertyFileName) {
        if (connection == null) {
            try {
                Properties props= DBPropertyUtil.loadProperties(propertyFileName);
                String driver = props.getProperty("driver");
                String url = props.getProperty("url");
                String username = props.getProperty("username");
                String password = props.getProperty("password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
