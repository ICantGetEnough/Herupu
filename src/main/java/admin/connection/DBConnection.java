package admin.connection;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() throws Exception{
        Properties prop = new Properties();
        prop.load(new FileReader("C:\\Users\\aliaq\\IdeaProjects\\Testing_Hospital\\src\\main\\config.properties"));

        Connection con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("user"),prop.getProperty("password"));
        return con;
    }
}
