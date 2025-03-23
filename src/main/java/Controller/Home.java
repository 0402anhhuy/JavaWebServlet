package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "home", urlPatterns = "/home")
public class Home extends HttpServlet {
    // Hàm khởi tạo
    @Override
    public void init() throws ServletException {
        System.out.println("Hello Servlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(username == null) {
            resp.getWriter().write("<h1>Error</h1>");
            username = "World";
        }
        resp.getWriter().write("Hello " + username);
        System.out.println("Kiem tra " + username);
    }
}
