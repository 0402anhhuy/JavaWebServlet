package Service;

import Model.User;
import Repository.UserRepository;
import java.util.List;

@SuppressWarnings("unused")
public class LoginService {
    private final UserRepository userRepository = new UserRepository();
    /*
        Nhận giá trị username, password từ Web (HTTP Request), APIs
    */
    public boolean checkLogin(String username, String password) {
        List<User> users = userRepository.getUserByUsernameAndPassword(username, password);
        return !users.isEmpty();
    }
}
