<%@page import="entities.User"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    <jsp:include page="searchBar.jsp"/>
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

            <%
                ArrayList<User> users = (ArrayList<User>) request.getAttribute("LIST_USER");
                if (users != null) {
                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
            %>

            <div class="user-item">

                <div class="user-bar">
                    <div class="user-email"><%= user.getEmail()%></div>
                    <div class="action-buttons">
                        <button class="btn-edit" onclick="toggleEditPanel('edit-panel-<%= i%>')">Edit</button>
                        <button class="btn-delete">Delete</button>
                    </div>
                </div>

                <div id="edit-panel-<%= i%>" class="edit-panel">
                    <form action="UpdateUserController" method="POST">
                        <div class="form-group">
                            <label>Email (Không thể sửa)</label>
                            <input type="email" name="email" value="<%= user.getEmail()%>" readonly style="background-color: #e9ecef;">
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="text" name="password" value="<%= user.getPassword()%>">
                        </div>
                        <div class="form-group">
                            <label>Phone Number</label>
                            <input type="text" name="phoneNumber" value="<%= user.getPhoneNumber()%>">
                        </div>
                        <div class="form-group">
                            <label>Role</label>
                            <input type="text" name="role" value="<%= user.getRole()%>">
                        </div>

                        <button type="submit" class="btn-save">Lưu thay đổi</button>
                    </form>
                </div>

            </div> <%
                    }
                }
            %>
        </div>

        <div id="addUserModal" class="modal-overlay">
            <div class="modal-content">
                <span class="close-btn" onclick="closeAddModal()">&times;</span>
                <div class="modal-header">Thêm User Mới</div>

                <form action="AddUserController" method="POST">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="text" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="text" name="phoneNumber" required>
                    </div>
                    <div class="form-group">
                        <label>Role</label>
                        <input type="text" name="role" required>
                    </div>
                    <button type="submit" class="btn-save btn-submit-modal">Tạo User</button>
                </form>
            </div>
        </div>

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
        </script>
    </body>
</html>