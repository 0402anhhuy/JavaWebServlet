package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "roleServlet")
public class RoleController extends HttpServlet {
    public void init() {
        System.out.println("Hello RoleController");
    }
}
