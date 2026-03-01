<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <title>Đăng Nhập</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="taskBar.css"/>
        <link rel="stylesheet" href="lgAndSignUp.css"/>
    </head>
    <body>

        <div>
            <div class="taskBar">
                <div class="taskBar_menu">
                    <a class="taskBar_menu_btn" href="GetCoursesController">Home</a>
                </div>
                <div class="taskBar_search"></div>
                <div class="taskBar_signUp_login"></div>
            </div>
        </div>

        <div class="auth_body">
            <div class="wrapper">
                <div class="title-text">
                    <div class="title login">Login Form</div>
                </div>

                <div class="slide-controls">
                    <a href="#" class="slide active">Login</a>
                    <a href="signup.jsp" class="slide">Signup</a>
                </div>

                <div class="form-container">
                    <div class="form-inner">
                        <form action="LoginController" method="post" class="login">
                            <div class="field">
                                <input type="text" name="txtemail" 
                                       oninvalid="this.setCustomValidity('Vui lòng nhập Email')"
                                       oninput="this.setCustomValidity('')"
                                       placeholder="Email Address" required="">
                            </div>
                            <div class="field">
                                <input type="password" name="txtpassword" 
                                       oninvalid="this.setCustomValidity('Vui lòng nhập Mật Khẩu')"
                                       oninput="this.setCustomValidity('')"
                                       placeholder="Password" required="">
                            </div>
                            <div class="pass-link">
                                <a href="#">Forgot password?</a>
                            </div>
                            <div><%
                                String msg = (String) request.getAttribute("ERROR");
                                if (msg != null) {
                                    out.print("<p style='color: red'>");
                                    out.print(msg);
                                    out.print("</p>");
                                }
                                %></div>
                            <div class="field btn">
                                <div class="btn-layer"></div>
                                <input type="submit" value="Login">
                            </div>
                            <div class="signup-link">
                                Create an account <a href="signup.jsp">Signup now</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>