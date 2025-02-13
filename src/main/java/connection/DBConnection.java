package connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        Properties prop = new Properties();
        try {
            prop.load(new FileReader("C:\\Users\\aliaq\\IdeaProjects\\Testing_Hospital\\src\\main\\config.properties"));
            Connection con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
            return con;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
