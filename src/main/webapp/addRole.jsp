<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>Thêm Vai Trò</title>
    <style>
        body {
            background-color: #f4e1ff;
            font-family: 'Arial', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            background-color: #e6ccff;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 400px;
            text-align: center;
        }
        .form-title {
            color: #5a189a;
            font-size: 20px;
            margin-bottom: 15px;
        }
        .form-group {
            text-align: left;
            margin-bottom: 15px;
        }
        .form-label {
            font-weight: bold;
            color: #5a189a;
            display: block;
            margin-bottom: 5px;
        }
        .form-control {
            width: 100%;
            padding: 10px;
            border-radius: 8px;
            border: 1px solid #b48de2;
            font-size: 14px;
            transition: 0.3s;
        }
        .form-control:focus {
            border-color: #7b2cbf;
            box-shadow: 0 0 8px rgba(123, 44, 191, 0.5);
            outline: none;
        }
        .btn-submit {
            background-color: #5a189a;
            color: white;
            border-radius: 8px;
            padding: 10px;
            width: 100%;
            border: none;
            font-size: 16px;
            transition: 0.3s;
            cursor: pointer;
        }
        .btn-submit:hover {
            background-color: #3c096c;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h4 class="form-title">ADD ROLE</h4>
    <form action="AddRoleServlet" method="post">
        <div class="form-group">
            <label class="form-label">Full Name</label>
            <input type="text" name="role_name" class="form-control" placeholder="Enter full name" required>
        </div>
        <div class="form-group">
            <label class="form-label">Position</label>
            <input type="text" name="role_position" class="form-control" placeholder="Enter position" required>
        </div>
        <div class="form-group">
            <label class="form-label">Office</label>
            <input type="text" name="role_office" class="form-control" placeholder="Enter office location" required>
        </div>
        <div class="form-group">
            <label class="form-label">Age</label>
            <input type="number" name="role_age" class="form-control" placeholder="Enter age" required>
        </div>
        <div class="form-group">
            <label class="form-label">Start Date</label>
            <input type="date" name="role_start_date" class="form-control" required>
        </div>
        <div class="form-group">
            <label class="form-label">Salary</label>
            <input type="number" name="role_salary" class="form-control" placeholder="Enter salary" required>
        </div>
        <button type="submit" class="btn-submit">Confirm</button>
    </form>
</div>
</body>
</html>
