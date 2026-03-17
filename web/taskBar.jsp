
<%@page import="entities.Student"%>


<%@page import="entities.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Trang chủ</title>

        <title>JSP Page</title>

        <link rel="stylesheet" href="taskBar.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    </head>
    <body>
        <%
            // Lấy User từ Session 
            User user = (User) session.getAttribute("LOGIN_USER");
        %>

        <div class="taskBar">
            <div class="taskBar_menu">
                <a class="taskBar_menu_btn" href="javascript:void(0)" onclick="openNav()">Menu</a>
                <div id="mySidebar" class="sidebar">
                    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                    <div class="menu-group">
                        <h3 class="menu-title">Quản lý đào tạo</h3>
                        <a href="GetCoursesController">Trang chủ</a>
                        <a href="#">Giảng viên</a>
                        <%
                            // Lấy đối tượng user từ session đã lưu ở LoginController
                            if (user != null && "Admin".equals(user.getRole())) {
                        %>
                        <a href="UserManagerController">Quản lý người dùng</a>
                        <a href="BannedUserController">Người dùng bị Ban</a>
                        <a href="CourseManagerController">Quản lý khóa học</a>
                        <a href="LogoutController">Đăng xuất</a>                         
                        <%
                            }
                        %>
                    </div>

                </div>
                <div id="myOverlay" class="overlay" onclick="closeNav()"></div>
            </div>

            <% if (user == null) { %>
            <div class="taskBar_signUp_login">
                <div class="taskBar_signUp">
                    <a class="taskBar_signUp_btn" href="signup.jsp">Sign Up</a>
                </div>
                <div class="taskBar_login">
                    <a class="taskBar_login_btn" href="login.jsp">Login</a>
                </div>
            </div>
            <% } else if (user.getRole().equals("Student")) {%>
            <%Student student = (Student) session.getAttribute("LOGIN_USER");%>
            <div class="user-account" style="display: flex; align-items: center; gap: 10px;">
                <span class="user-greeting">Hi, <%= student.getName()%></span> 
                <div class="user-icon-box">
                    <i class="fa-solid fa-user"></i>

                    <div class="dropdown-menu">
                        <a href="accountSetting.jsp">Cài đặt tài khoản</a>
                        <a href="MyCourseController">Khóa học</a>
                        <a href="LogoutController">Đăng xuất</a>
                    </div>
                </div>

            </div>
            <% } else if (user.getRole().equals("Admin")) {%>
            <div class="user-account" style="display: flex; align-items: center; gap: 10px;">
                <div class="user-icon-box">
                    <i class="fa-solid fa-user"></i>
                </div>
            </div>
            <%}%>


        </div>

        <script>
            function openNav() {
                document.getElementById("mySidebar").style.left = "0";
                document.getElementById("myOverlay").style.display = "block";
            }

            function closeNav() {
                document.getElementById("mySidebar").style.left = "-300px";
                document.getElementById("myOverlay").style.display = "none";
            }
        </script>
    </body> 
</html>