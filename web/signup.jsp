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
                    <form action="SignupServlet" method="post" class="signup">
                        <div class="field">
                            <input type="text" name="email" placeholder="Email Address" required>
                        </div>
                        <div class="field">
                            <input type="password" name="password" placeholder="Password" required>
                        </div>
                        <div class="field">
                            <input type="password" name="repassword" placeholder="Confirm password" required>
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

</body>
</html>