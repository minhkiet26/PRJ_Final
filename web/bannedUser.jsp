<%@page import="entities.Teacher"%>
<%@page import="entities.Student"%>
<%@page import="entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách người dùng bị chặn</title>
        <link rel="stylesheet" href="showUser.css"> 
    </head>
    <body>
        <jsp:include page="taskBar.jsp"/>

        <div class="user-list-container">
            <div class="top-controls">
                <div class="search-wrapper">
                    <jsp:include page="searchBar.jsp">
                        <jsp:param name="searchTarget" value="bannedUser"/>
                    </jsp:include>
                </div>
            </div>

            <h2 class="section-title">Banned Teacher List</h2>
            <%
                ArrayList<Teacher> teacherList = (ArrayList<Teacher>) request.getAttribute("LIST_USER_TEACHER");
                ArrayList<Teacher> teacherListSearch = (ArrayList<Teacher>) request.getAttribute("LIST_TEACHER_SEARCH");
                ArrayList<Teacher> listToDisplay = (teacherListSearch != null) ? teacherListSearch : teacherList;
                
                if (listToDisplay != null && !listToDisplay.isEmpty()) {
                    for (Teacher t : listToDisplay) {
                        if ("Banned".equalsIgnoreCase(t.getStatus())) {
            %>
            <div class="user-item teacher-item">
                <div class="user-bar">
                    <div class="user-email">
                        <strong>[Teacher]</strong> <%= t.getEmail()%> - <%= t.getName()%>
                    </div>
                    <div class="action-buttons">
                        <a href="#" onclick="confirmUnban('<%= t.getEmail()%>')" class="btn-delete" style="background-color: #28a745;">Unban</a> 
                    </div>
                </div>
            </div>
            <%
                        }
                    }
                }
            %>

            <h2 class="section-title">Banned Student List</h2>
            <%
                ArrayList<Student> studentList = (ArrayList<Student>) request.getAttribute("LIST_USER_STUDENT");
                ArrayList<Student> studentListSearch = (ArrayList<Student>) request.getAttribute("LIST_STUDENT_SEARCH");
                ArrayList<Student> listToDisplayStudent = (studentListSearch != null) ? studentListSearch : studentList;
                
                if (listToDisplayStudent != null && !listToDisplayStudent.isEmpty()) {
                    for (Student s : listToDisplayStudent) {
                        if ("Banned".equalsIgnoreCase(s.getStatus())) {
            %>
            <div class="user-item student-item">
                <div class="user-bar">
                    <div class="user-email">
                        <strong>[Student]</strong> <%= s.getEmail()%> - <%= s.getName()%>
                    </div>
                    <div class="action-buttons">
                        <a href="#" onclick="confirmUnban('<%= s.getEmail()%>')" class="btn-delete" style="background-color: #28a745;">Unban</a> 
                    </div>
                </div>
            </div>
            <%
                        }
                    }
                }
            %>
        </div>

        <script>
            // Hiệu ứng cuộn trượt từ trang cũ
            document.addEventListener("DOMContentLoaded", function () {
                const observer = new IntersectionObserver((entries) => {
                    entries.forEach(entry => {
                        if (entry.isIntersecting) {
                            entry.target.classList.add('show-on-scroll');
                        } else {
                            entry.target.classList.remove('show-on-scroll');
                        }
                    });
                }, {threshold: 0.1});

                document.querySelectorAll('.user-item').forEach(item => {
                    observer.observe(item);
                });
            });

            // Hàm xác nhận Unban
            function confirmUnban(email) {
                if (confirm("Xác nhận MỞ KHÓA cho người dùng: " + email + "?")) {
                    window.location.href = "UnbanUserController?email=" + email;
                }
            }
        </script>

        <%-- Hiển thị thông báo STATUS từ Session nếu có --%>
        <c:if test="${not empty sessionScope.STATUS}">
            <script>alert("${sessionScope.STATUS}");</script>
            <c:remove var="STATUS" scope="session" />
        </c:if>
    </body>
</html>