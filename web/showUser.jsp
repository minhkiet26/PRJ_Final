<%@page import="entities.Teacher"%>
<%@page import="entities.Student"%>
<%@page import="entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:if test="${sessionScope.LOGIN_USER == null}">
    <c:redirect url="login.jsp"/>
</c:if>
<c:if test="${sessionScope.LOGIN_USER != null}">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
        <link rel="stylesheet" href="showUser.css"> 
    </head>
    <body>
        <jsp:include page="taskBar.jsp"/>

        <div class="user-list-container">
            <div class="top-controls">
                <button class="btn-add-circle" title="Thêm mới User" onclick="openAddModal()">+</button>
                <div class="search-wrapper">
                    <jsp:include page="searchBar.jsp">
                        <jsp:param name="searchTarget" value="user"/>
                    </jsp:include>
                </div>
                <div class="view_student">
                    <%
                        Integer total = (Integer) request.getAttribute("TOTAL_STUDENT");
                        if (total != null) {
                    %>
                    <p style="font-weight: bold; color: #0085FF;">Tổng số sinh viên: <%= total%></p>
                    <%
                        }
                    %>
                </div>
            </div>

            <h2 class="section-title">Teacher List</h2>
            <%
                ArrayList<Teacher> teacherList = (ArrayList<Teacher>) request.getAttribute("LIST_USER_TEACHER");
                ArrayList<Teacher> teacherListSearch = (ArrayList<Teacher>) request.getAttribute("LIST_TEACHER_SEARCH");
                ArrayList<Teacher> listToDisplay = (teacherListSearch != null) ? teacherListSearch : teacherList;
                if (listToDisplay != null && !listToDisplay.isEmpty()) {
                    for (Teacher t : listToDisplay) {
                        if ("Active".equalsIgnoreCase(t.getStatus())) {
            %>
            <div class="user-item teacher-item">
                <div class="user-bar">
                    <div class="user-email"><strong>[Teacher]</strong> <%= t.getEmail()%> - <%= t.getName()%></div>
                    <div class="action-buttons">
                        <button class="btn-edit" onclick="toggleEditPanel('edit-teacher-<%= t.getTeacherID()%>')">Edit</button>
                        <a href="#" onclick="confirmBan('<%= t.getEmail()%>')" class="btn-delete">Ban</a>
                    </div>
                </div>

                <div id="edit-teacher-<%= t.getTeacherID()%>" class="edit-panel">
                    <form action="UpdateUserController" method="POST">
                        <input type="hidden" name="role" value="Teacher">
                        <div class="field">
                            <label>Email</label>
                            <input type="email" name="email" value="<%= t.getEmail()%>" readonly class="readonly-input">
                        </div>
                        <div class="field">
                            <label>Full Name</label>
                            <input type="text" name="name" value="<%= t.getName()%>">
                        </div>
                        <div class="field">
                            <label>Password</label>
                            <input type="text" name="password" value="<%= t.getPassword()%>">
                        </div>
                        <div class="field">
                            <label>Phone</label>
                            <input type="text" name="phoneNumber" value="<%= t.getPhoneNumber()%>">
                        </div>
                        <button type="submit" class="btn-save">Lưu Teacher</button>
                    </form>
                </div>
            </div>
            <%
                        }
                    }
                }
            %>

            
            
            <h2 class="section-title">Student List</h2>
            <%
                ArrayList<Student> studentList = (ArrayList<Student>) request.getAttribute("LIST_USER_STUDENT");
                ArrayList<Student> studentListSearch = (ArrayList<Student>) request.getAttribute("LIST_STUDENT_SEARCH");
                ArrayList<Student> listToDisplayStudent = (studentListSearch != null) ? studentListSearch : studentList;
                if (listToDisplayStudent != null && !listToDisplayStudent.isEmpty()) {
                    for (Student s : listToDisplayStudent) {
                        if ("Active".equalsIgnoreCase(s.getStatus())) {
            %>
            <div class="user-item student-item">
                <div class="user-bar">
                    <div class="user-email"><strong>[Student]</strong> <%= s.getEmail()%> - <%= s.getName()%></div>
                    <div class="action-buttons">
                        <button class="btn-edit" onclick="toggleEditPanel('edit-student-<%= s.getStudentID()%>')">Edit</button>
                        <a href="#" onclick="confirmBan('<%= s.getEmail()%>')" class="btn-delete">Ban</a>
                    </div>
                </div>

                <div id="edit-student-<%= s.getStudentID()%>" class="edit-panel">
                    <form action="UpdateUserController" method="POST">
                        <input type="hidden" name="role" value="Student">
                        <div class="field">
                            <label>Email</label>
                            <input type="email" name="email" value="<%= s.getEmail()%>" readonly class="readonly-input">
                        </div>
                        <div class="field">
                            <label>Full Name</label>
                            <input type="text" name="name" value="<%= s.getName()%>">
                        </div>
                        <div class="field">
                            <label>Password</label>
                            <input type="text" name="password" value="<%= s.getPassword()%>">
                        </div>
                        <div class="field">
                            <label>Phone</label>
                            <input type="text" name="phoneNumber" value="<%= s.getPhoneNumber()%>">
                        </div>
                        <button type="submit" class="btn-save">Lưu Student</button>
                    </form>
                </div>
            </div>
            <%
                        }
                    }
                }
            %>
        </div>

        <div id="addUserModal" class="modal-overlay">
            <div class="modal-content">
                <span class="close-btn" onclick="closeAddModal()">&times;</span>
                <div class="modal-header">Thêm User Mới</div>

                <form action="AddUserController" method="POST" class="signup" novalidate>
                    <div class="field">
                        <label>Email</label>
                        <input type="text" name="EMAIL"
                               required="" value="${oldEmail}" oninput="checkEmail()">
                        <span id="error-msg-email" style="color: red; font-size: 12px; display: block;"></span>
                        <span id="server-error-email" style="color: red; font-size: 12px; display: block;">${errorMessage}</span>
                    </div>

                    <div class="field">
                        <label>Password</label>
                        <input type="password" name="PASSWORD" 
                               required>
                        <span id="error-msg-password" style="color: red; font-size: 12px; display: block;"></span>
                    </div>

                    <div class="field">
                        <label>Phone Number</label>
                        <input type="text" name="PHONENUMBER" required>
                    </div>

                    <div class="role-container">
                        <label class="role-option">
                            <input type="radio" name="ROLE" value="Student" onclick="toggleFullName(true)" required=""> Student
                        </label>
                        <label class="role-option">
                            <input type="radio" name="ROLE" value="Teacher" onclick="toggleFullName(true)" required=""> Teacher
                        </label>
                    </div>
                    <span id="error-msg-role" style="color: red; font-size: 12px; display: block;"></span>

                    <div class="field hidden" id="fullNameField">
                        <label>FullName</label>
                        <input type="text" name="FULLNAME"  
                               required="">
                        <span id="error-msg-name" style="color: red; font-size: 12px; display: block;"></span>
                    </div>
                    <button type="submit" class="btn-submit-green">Tạo User</button>

                </form>
            </div>
        </div>

        <script src="checkValid.js"></script>
        <script>
                                // Hiệu ứng xổ xuống từ từ cho bảng Edit (sử dụng classList thay vì style.display)
                                function toggleEditPanel(panelId) {
                                    var panel = document.getElementById(panelId);
                                    panel.classList.toggle("show");
                                }

                                // Quản lý Bảng Modal Thêm Mới
                                function openAddModal() {
                                    document.getElementById("addUserModal").style.display = "flex";
                                }

                                function closeAddModal() {
                                    document.getElementById("addUserModal").style.display = "none";
                                }

                                window.onclick = function (event) {
                                    var modal = document.getElementById("addUserModal");
                                    if (event.target == modal) {
                                        modal.style.display = "none";
                                    }
                                }

                                // --- cho hiệu ứng cuộn sang trái ---
                                document.addEventListener("DOMContentLoaded", function () {
                                    // Tạo một IntersectionObserver để theo dõi liên tục
                                    const observer = new IntersectionObserver((entries) => {
                                        entries.forEach(entry => {
                                            if (entry.isIntersecting) {
                                                // Khi phần tử lọt vào tầm nhìn màn hình -> Thêm class để trượt ra & rõ dần
                                                entry.target.classList.add('show-on-scroll');
                                            } else {
                                                // Khi phần tử trượt qua, khuất khỏi tầm nhìn màn hình -> Xóa class để nó thụt vô & mờ đi
                                                entry.target.classList.remove('show-on-scroll');
                                            }
                                        });
                                    }, {
                                        // Threshold: chỉ cần 10% cái que lọt vào màn hình là nó bắt đầu hiệu ứng
                                        threshold: 0.1
                                    });

                                    // gắn bộ theo dõi 
                                    document.querySelectorAll('.user-item').forEach(item => {
                                        observer.observe(item);
                                    });
                                });
                                function confirmBan(email) {
                                    if (confirm("Xác nhận BAN người dùng có email: " + email + "?")) {
                                        window.location.href = "BanUser?txtEmail=" + email;
                                    }
                                }
        </script>
        <c:if test="${not empty sessionScope.STATUS}">
            <script>
                alert("${sessionScope.STATUS}");
            </script>
            <c:remove var="STATUS" scope="session" />
        </c:if>
    </body>
</html>
</c:if>