<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <title>Đăng Ký</title>
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
                    <div class="title signup">Signup Form</div>
                </div>

                <div class="slide-controls">
                    <a href="login.jsp" class="slide">Login</a>
                    <a href="#" class="slide active">Signup</a>
                </div>

                <div class="form-container">
                    <div class="form-inner">
                        <form action="SignUpController" method="post" class="signup" novalidate>
                            <div class="field">
                                <input type="text" name="EMAIL"
                                       placeholder="Email Address" required="" value="${oldEmail}" oninput="checkEmail()">
                                <span id="error-msg-email" style="color: red; font-size: 12px; display: block;"></span>
                                <span id="server-error-email" style="color: red; font-size: 12px; display: block;">${errorMessage}</span>
                            </div>
                            <div class="field">
                                <input type="password" name="PASSWORD" 
                                       placeholder="Password" required>
                                <span id="error-msg-password" style="color: red; font-size: 12px; display: block;"></span>
                            </div>

                            <div class="field">
                                <input type="password" name="CONFIRM_PASSWORD" 
                                       placeholder="Confirm password" required="">
                                <span id="error-msg-confirmpassword" style="color: red; font-size: 12px; display: block;"></span>
                            </div>
                            <div class="role-container">
                                <label class="role-option">
                                    <input type="radio" name="ROLE" value="student" onclick="toggleFullName(true)" required=""> Student
                                </label>
                                <label class="role-option">
                                    <input type="radio" name="ROLE" value="teacher" onclick="toggleFullName(true)" required=""> Teacher
                                </label>
                            </div>
                            <span id="error-msg-role" style="color: red; font-size: 12px; display: block;"></span>

                            <div class="field hidden" id="fullNameField">
                                <input type="text" name="FULLNAME"  
                                       placeholder="Full Name" required="">
                                <span id="error-msg-name" style="color: red; font-size: 12px; display: block;"></span>
                            </div>
                            <div class="field btn">
                                <div class="btn-layer"></div>
                                <input type="submit" value="Signup">
                            </div>
                            <div class="signup-link">
                                Already have an account? <a href="login.jsp">Login now</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script src="checkValid.js"></script>
    </body>
</html>