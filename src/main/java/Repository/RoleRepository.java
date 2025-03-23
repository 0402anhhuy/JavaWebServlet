package Repository;

import Config.MySQLConnection;
import Model.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class RoleRepository {
    /*
        Khi lấy dữ liệu từ Database -> trả về 1 list (1 bảng ghi trong database)
    */
    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();

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
        String selectQuery = "SELECT * FROM roles";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setRole_id(resultSet.getInt("id"));
                role.setRole_name(resultSet.getString("role_name"));
                role.setRole_position(resultSet.getString("role_position"));
                role.setRole_office(resultSet.getString("role_office"));
                role.setRole_age(resultSet.getInt("role_age"));
                role.setRole_start_date(resultSet.getString("role_start_date"));
                role.setRole_salary(resultSet.getInt("role_salary"));
                roles.add(role);
            }
        } catch (SQLException e) {
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
        return roles;
    }

    public boolean deleteRolesById(int id){
        Connection connection = MySQLConnection.getConnection();
        String deleteQuery = "DELETE FROM roles WHERE id = ?";
        boolean isDeleteSuccess = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);

            int rows = preparedStatement.executeUpdate();
            isDeleteSuccess = (rows > 0);
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
        return isDeleteSuccess;
    }

    public boolean insertRoles(Role role){
        Connection connection = MySQLConnection.getConnection();
        String insertQuery = "INSERT INTO roles(role_name, role_position, role_office, role_age, role_start_date, role_salary) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, role.getRole_name());
            preparedStatement.setString(2, role.getRole_position());
            preparedStatement.setString(3, role.getRole_office());
            preparedStatement.setInt(4, role.getRole_age());
            preparedStatement.setString(5, role.getRole_start_date());
            preparedStatement.setInt(6, role.getRole_salary());

            int isInsertSuccess = preparedStatement.executeUpdate();
            return isInsertSuccess > 0;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
