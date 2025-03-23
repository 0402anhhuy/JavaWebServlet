package Repository;

import Config.MySQLConnection;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings("unused")
public class UserRepository {
    /*
        Khi lấy dữ liệu từ Database -> trả về 1 list (1 bảng ghi trong database)
    */
    public List<User> getUserByUsernameAndPassword(String username, String password){
        List<User> users = new ArrayList<>();

        /*
            1. Mở kết nối đến CSDL (trong Package Config)
                - Biến static nên không cần tạo dối tượng (dùng new)
            2. Tạo 1 câu truy vấn để lấy dữ liệu từ CSDL
                - Trong JDBC dùng ? để giữ chỗ (placeholders) cho tham số
            3. Chuẩn bị câu query
                - Tạo đối tượng PreparedStatement
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                - Thiết lập giá trị cho tham số
                    preparedStatement.setX(index, value);
                    +) X: là kiểu dữ liệu của value
                    +) index: là vị trí của tham số trong câu query
                - Thực thi lệnh
                    +) Với câu truy vấn SELECT -> executeQuery() -> trả về ResultSet
                    +) Với câu lệnh INSERT, UPDATE, DELETE -> executeUpdate() -> trả về ResultSet
        */
        Connection connection = MySQLConnection.getConnection();
        String query = "SELECT * FROM users u WHERE u.username = ? and u.password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setCreated_time(resultSet.getString("created_time"));
                users.add(user);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }
}
