
<%@page import="entities.Student"%>
<%@page import="entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="account-setting.css"> 
    </head>
    <body>
        <jsp:include page="taskBar.jsp"/>
        <%
            User u = (User) request.getAttribute("LOGIN_USER");
            Student s = (Student) request.getAttribute("LOGIN_USER");
        %>

        <div class="container">
            <div class="account-setting">
                <h2 class="header" style="margin-top: 20px;">Chi tiết tài khoản</h2>

                <form action="UpdateStudentInfor" method="post" class="setting" novalidate>
                    <div class="form-group">
                        <label>Xác nhận tên</label>
                        <input type="text" name="newName" class="form-control" required="">
                        <span id="error-msg-name" style="color: red; font-size: 12px; display: block;" ></span>
                    </div>

                    <div class="form-group">
                        <label>Xác nhận mật khẩu</label>
                        <input type="password" name="newPass" class="form-control" required="">
                        <span id="error-msg-password" style="color: red; font-size: 12px; display: block;" ></span>
                    </div>

                    <div class="form-group">
                        <label>Xác nhận lại mật khẩu</label>
                        <input type="password" name="confirmPass" class="form-control" required="">
                        <span id="error-msg-confirmpassword" style="color: red; font-size: 12px; display: block;" ></span>
                    </div>

                    <div class="form-group">
                        <label>Xác nhận điện thoại</label> 
                        <input type="text" name="newPhone" class="form-control" required="">
                        <span id="error-msg-phone" style="color: red; font-size: 12px; display: block;" ></span>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn-save">Lưu thay đổi</button>
                    </div>
                </form>
            </div>
        </div>

        <%
            String error_pass = (String) request.getAttribute("ERROR");
            String error = (String) request.getAttribute("UPDATE_FAIL");
            String success = (String) request.getAttribute("UPDATE_SUCCESSFULL");
        %>

        <% if (error_pass != null) {%>
        <p style="color: red; font-weight: bold; margin-top: 10px;">
            <i class="fa-solid fa-triangle-exclamation"></i> <%= error_pass%>
        </p>
        <% } %>

        <% if (error != null) {%>
        <p style="color: red; font-weight: bold; margin-top: 10px;">
            <i class="fa-solid fa-triangle-exclamation"></i> <%= error%>
        </p>
        <% } %>

        <% if (success != null) {%>
        <p style="color: green; font-weight: bold; margin-top: 10px;">
            <i class="fa-solid fa-check-circle"></i> <%= success%>
        </p>
        <% }%>

        <script>
            // Đảm bảo trang tải xong mới chạy
            document.addEventListener("DOMContentLoaded", function () {

                const settingForm = document.querySelector('form.setting');

                // Kiểm tra xem tìm thấy form không (tránh lỗi nếu đổi tên class)
                if (settingForm) {
                    settingForm.addEventListener('submit', function (event) {
                        // Gọi các hàm kiểm tra
                        checkName();
                        validatePassword();
                        checkPhone();

                        // Nếu có lỗi (checkValidity false) thì chặn submit
                        if (!this.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                    });
                }
            });

            function validatePassword() {
                const password = document.querySelector('input[name="newPass"]');
                const confirm = document.querySelector('input[name="confirmPass"]');
                const errorMsg = document.getElementById('error-msg-password');
                const errorMsgConfirm = document.getElementById('error-msg-confirmpassword');

                if (password.value.trim() === "") {
                    errorMsg.innerText = "Mật khẩu không được để trống!";
                    password.setCustomValidity("invalid");
                } else {
                    errorMsg.innerText = "";
                }

                // Kiểm tra khớp
                if (confirm.value !== password.value) {
                    errorMsgConfirm.innerText = "Mật khẩu xác nhận không khớp!";
                    confirm.setCustomValidity("invalid");
                } else {
                    errorMsgConfirm.innerText = "";
                }
            }

            function checkName() {
                const name = document.querySelector('input[name="newName"]');
                const errorMsg = document.getElementById('error-msg-name');

                if (name.value.trim() === "") {
                    //viết lỗi để gửi
                    errorMsg.innerText = "Tên không được để trống!";
                    name.setCustomValidity("invalid");
                } else if (!validateName(name.value)) {
                    errorMsg.innerText = "Định dạng tên không hợp lệ";
                    name.setCustomValidity("invalid");
                } else {
                    errorMsg.innerText = "";
                    name.setCustomValidity("");
                }
            }

            function checkPhone() {
                const phone = document.querySelector('input[name="newPhone"]');
                const errorMsg = document.getElementById('error-msg-phone');

                if (phone.value.trim() === "") {
                    //viết lỗi để gửi
                    errorMsg.innerText = "Số điện thoại không được để trống!";
                    phone.setCustomValidity("invalid");
                } else if (!validatePhone(phone.value)) {
                    errorMsg.innerText = "Định dạng số điện thoại không hợp lệ";
                    phone.setCustomValidity("invalid");
                } else {
                    errorMsg.innerText = "";
                    phone.setCustomValidity("");
                }
            }

            function validateName(name) {
                const regex = /^[\p{L}\s]+$/u;
                return regex.test(name);
            }

            function validatePhone(phone) {
                const regex = /^0[3|5|7|8|9][0-9]{8}$/;
                return regex.test(phone);
            }
        </script>

    </body>
</html>
