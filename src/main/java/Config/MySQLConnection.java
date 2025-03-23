package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class MySQLConnection {
    // Database
    private static final String url = "jdbc:mysql://localhost:3306/db";
    private static final String username = "root";
    private static final String password = "Huy0402***+++";
    /*
        Tạo hàm static vì hàm getConnection sẽ được gọi liên tục (tránh new thêm nhiều đối tượng mới)
    */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            //Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Tạo kết nối với Database
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Unable to load driver class.");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Error: Unable to connect to database.");
            throw new RuntimeException(e);
        }
        return connection;
    }
}
