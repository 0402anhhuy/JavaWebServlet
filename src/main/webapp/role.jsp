<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Role" %>
<!DOCTYPE html>
<html>
<head>
    <title>Roles Table</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Roles Table</h2>
        <button class="btn btn-success" id="btn-add">Add Role</button>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Position</th>
            <th>Office</th>
            <th>Age</th>
            <th>Start date</th>
            <th>Salary</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Role> roles = (List<Role>) request.getAttribute("roles");
            if (roles != null) {
                for (Role role : roles) {
        %>
        <tr>
            <td><%= role.getRole_id() %></td>
            <td><%= role.getRole_name() %></td>
            <td><%= role.getRole_position() %></td>
            <td><%= role.getRole_office() %></td>
            <td><%= role.getRole_age() %></td>
            <td><%= role.getRole_start_date() %></td>
            <td>$<%= role.getRole_salary() %></td>
            <td>
                <button class="btn btn-danger btn-sm btn-delete" id=<%= role.getRole_id() %>>Delete</button>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8" class="text-center">No data available</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <%--Thêm JQuery--%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="role.js"></script>
    <script>
        $(document).ready(function() {
            $('#btn-add').click(function() {
                window.location.href = 'addRole.jsp';
            });
        });
    </script>
</div>
</body>
</html>
