package Controller;

import Service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = "/login")
public class Login extends HttpServlet {
    private final LoginService loginService = new LoginService();

    // Khi thêm vào url /login thì sẽ chuyển trang bằng doGet() -> gửi yêu cầu của người dùng
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isLogin = loginService.checkLogin(username, password);
        if(isLogin){
            // getContextPath() -> trả lại đường dẫn chính xác
            resp.sendRedirect(req.getContextPath() + "/GUI/index.html?username=" + username);
        }
        else{
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
        System.out.println(isLogin);
    }
}
