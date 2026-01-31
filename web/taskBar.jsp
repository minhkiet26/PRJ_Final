
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="taskBar.css"/>
    </head>
    <body>
        <div>
            <div class="taskBar">

                <div class="taskBar_menu">
                    <a class="taskBar_menu_btn" href="javascript:void(0)" onclick="openNav()">Menu</a>
                    <div id="mySidebar" class="sidebar">
                        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>

                        <div class="menu-group">
                            <h3 class="menu-title">Quản lý đào tạo</h3>
                            <a href="#"> Khóa học</a>
                            <a href="#"> Giảng viên</a>
                            <a href="#"> Thông tin cá nhân</a>
                            <a href="LogoutController">Đăng xuất</a> //khi làm hiển thị thông tin người dùng hãy đem thẻ a này qua đó
                        </div>
                    </div>
                    <div id="myOverlay" class="overlay" onclick="closeNav()"></div>
                </div>

                <div class="taskBar_search">
                    <div class="search_wrapper">
                        <form action="Search" method="post" style="display: flex; align-items: center; width: 100%;">
                            <input class="search_pill_text" type="text" placeholder="Search" name="textSearch">
                            <button class="search_pill_btn" type="submit" name="btnSearch">
                                <svg viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M21 21L15 15M17 10C17 13.866 13.866 17 10 17C6.13401 17 3 13.866 3 10C3 6.13401 6.13401 3 10 3C13.866 3 17 6.13401 17 10Z" stroke="white" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                                </svg>
                            </button>
                        </form>
                    </div>
                </div>

                <%
                    User u = (User) session.getAttribute("LOGIN_USER");//lấy User đã lưu trong session
                    if (u != null) {
                        out.print("<div class='user-container'>");
                        out.print("<a href='profile' class='user-link' title='Thông tin cá nhân'>");
                        out.print("<i class='fa-solid fa-circle-user'></i>");
                        out.print(" </a>");
                        out.print("</div>");
                    } else {
                        out.print("<div class='taskBar_signUp_login'>");
                        out.print("    <div class='taskBar_signUp'>");
                        out.print("        <a class='taskBar_signUp_btn' href='signup.jsp'>Sign Up</a>");
                        out.print("    </div>");
                        out.print("    <div class='taskBar_login'>");
                        out.print("        <a class='taskBar_login_btn' href='login.jsp'>Login</a>");
                        out.print("    </div>");
                        out.print("</div>");
                    }
                %>



            </div>
        </div>

        <script>
            function openNav() {
                document.getElementById("mySidebar").style.left = "0"; // Hiện menu
                document.getElementById("myOverlay").style.display = "block"; // Hiện lớp phủ
            }

            function closeNav() {
                document.getElementById("mySidebar").style.left = "-300px"; // Ẩn menu
                document.getElementById("myOverlay").style.display = "none"; // Ẩn lớp phủ
            }
        </script>

    </body>
</html>
