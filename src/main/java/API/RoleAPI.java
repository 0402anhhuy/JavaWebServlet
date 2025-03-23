package API;

import Common.Constant;
import Model.Role;
import Payload.ResponseData;
import Service.RoleService;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@SuppressWarnings("unused")
@WebServlet(name = "rolesAPI", urlPatterns = {Constant.URL_ROLE_DELETE, Constant.URL_ROLE_ADD})
public class RoleAPI extends HttpServlet {
    private final RoleService roleService = new RoleService();
    private final Gson gson = new Gson();

    public RoleAPI(){
        System.out.println("Hello RoleAPI");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String urlPath = req.getServletPath();
        switch (urlPath){
            case Constant.URL_ROLE_DELETE:
                deleteRole(req, resp);
                break;
            case Constant.URL_ROLE_ADD:
                insertRole(req, resp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        try {
            if (Constant.URL_ROLE_ADD.equals(path)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/role.jsp");
                dispatcher.forward(req, resp);
            }
            else if (Constant.URL_ROLE_DELETE.equals(path)) {
                List<Role> roles = roleService.getRoles();
                req.setAttribute("roles", roles);

                RequestDispatcher dispatcher = req.getRequestDispatcher("/role.jsp");
                dispatcher.forward(req, resp);
            }
            else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy trang yêu cầu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi trong quá trình xử lý yêu cầu.");
        }
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();

        // Lấy tham số ID từ request
        String idParam = req.getParameter("id");
        ResponseData responseData = new ResponseData();
        System.out.println("Received ID: " + idParam);

        if(idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseData.setDeleteSuccess(false);
            responseData.setDescription("Missing role ID");
            responseData.setData(null);
        }
        else{
            try{
                int id = Integer.parseInt(idParam);
                boolean isDeleteSuccess = roleService.deleteRolesById(id);
                if(isDeleteSuccess){
                    resp.setStatus(HttpServletResponse.SC_OK);
                    responseData.setDeleteSuccess(true);
                    responseData.setDescription("Role deleted successfully");
                    responseData.setData(null);
                }
                else{
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    responseData.setDeleteSuccess(false);
                    responseData.setDescription("Role not found");
                    responseData.setData(null);
                }
            }
            catch(NumberFormatException e){
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseData.setDeleteSuccess(false);
                responseData.setDescription("Invalid role ID format");
                responseData.setData(null);
            }
            catch(Exception e){
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                responseData.setDeleteSuccess(false);
                responseData.setDescription("Error deleting role");
                responseData.setData(null);
                e.printStackTrace();
            }
        }
        String json = gson.toJson(responseData);
        printWriter.print(json);
        printWriter.flush();
    }
    private void insertRole(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ResponseData responseData = new ResponseData();
        PrintWriter printWriter = resp.getWriter();
        try {

            String roleName = req.getParameter("role_name");
            String rolePosition = req.getParameter("role_position");
            String roleOffice = req.getParameter("role_office");
            int roleAge = Integer.parseInt(req.getParameter("role_age"));
            String roleStartDate = req.getParameter("role_start_date");
            int roleSalary = Integer.parseInt(req.getParameter("role_salary"));

            if (roleName == null || roleName.trim().isEmpty() || rolePosition == null || rolePosition.trim().isEmpty() || roleOffice == null || roleOffice.trim().isEmpty() || roleStartDate == null || roleStartDate.trim().isEmpty()) {
                throw new IllegalArgumentException("Missing required fields");
            }

            Role role = new Role(roleName, rolePosition, roleOffice, roleAge, roleStartDate, roleSalary);
            boolean isInsertSuccess = roleService.insertRoles(role);

            if (isInsertSuccess) {
                resp.setStatus(HttpServletResponse.SC_OK);
                responseData.setInsertSuccess(true);
                responseData.setDescription("Role inserted successfully");
                responseData.setData(null);
            }
            else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                responseData.setInsertSuccess(false);
                responseData.setDescription("Failed to insert role");
                responseData.setData(null);
            }
        }
        catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            responseData.setInsertSuccess(false);
            responseData.setDescription("Invalid input format");
            responseData.setData(null);
        }
        catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            responseData.setInsertSuccess(false);
            responseData.setDescription("Error inserting role");
            responseData.setData(null);
            e.printStackTrace();
        }

        String json = gson.toJson(responseData);
        printWriter.print(json);
        printWriter.flush();
    }
}
