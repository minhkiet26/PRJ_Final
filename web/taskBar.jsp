
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
            Student student = (Student) session.getAttribute("LOGIN_USER");
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
                    </div>
                </div>
                <div id="myOverlay" class="overlay" onclick="closeNav()"></div>
            </div>

            <div class="taskBar_search">
                <div class="search_wrapper">
                    <form action="Search" method="post" style="display: flex; align-items: center; width: 100%;">
                        <input class="search_pill_text" type="text" placeholder="Search" name="textSearch">
                        <button class="search_pill_btn" type="submit" name="btnSearch">
                            <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" width="20" height="20">
                                <path d="M21 21L15 15M17 10C17 13.866 13.866 17 10 17C6.13401 17 3 13.866 3 10C3 6.13401 6.13401 3 10 3C13.866 3 17 6.13401 17 10Z" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </svg>
                        </button>
                    </form>
                </div>
            </div>

            <% if (student == null) { %>
                <div class="taskBar_signUp_login">
                    <div class="taskBar_signUp">
                        <a class="taskBar_signUp_btn" href="signup.jsp">Sign Up</a>
                    </div>
                    <div class="taskBar_login">
                        <a class="taskBar_login_btn" href="login.jsp">Login</a>
                    </div>
                </div>
            <% } else { %>
                <div class="user-account" style="display: flex; align-items: center; gap: 10px;">
                    <span class="user-greeting">Hi, <%= student.getName() %></span> 
                    <div class="user-icon-box">
                        <i class="fa-solid fa-user"></i>
                        
                        <div class="dropdown-menu">
                            <a href="profile.jsp">Thông tin cá nhân</a>
                            <a href="MyCourseController">Khóa học</a>
                            <a href="LogoutController">Đăng xuất</a>
                            <a href="accountSetting.jsp">Cài đặt tài khoản</a>
                        </div>
                    </div>
                </div>
            <% } %>

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

    </body> </html>