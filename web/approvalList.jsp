<%-- 
    Document   : approvalList
    Created on : Mar 16, 2026, 6:24:35 PM
    Author     : ACER
--%>

<%@page import="entities.Enrollment"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="showUser.css"> 
    </head>
    <body>
        <div class="user-list-container">
            <%
                ArrayList<Enrollment> list = (ArrayList<Enrollment>) request.getAttribute("LIST");
                if (list == null || list.isEmpty()) {
            %>
            <div style="text-align: center; margin-top: 50px; color: #666;">
                <h3>Chưa có sinh viên nào chờ duyệt cho môn học này!</h3>
            </div>
            <%
            } else {
                for (int i = 0; i < list.size(); i++) {
                    Enrollment e = list.get(i);

            %>
            <div class="user-item" >
                <div class="user-bar">
                    <div class="user-info" style="display: flex; flex-direction: column; gap: 5px;">
                        <div class="user-name" style="font-size: 18px; color: #0085FF; font-weight: bold;">
                            <i class="fa-solid fa-user"></i> <%= e.getStudent().getName()%>
                        </div>
                        <div class="user-email" style="font-size: 14px; color: #555;">
                            <i class="fa-solid fa-envelope"></i> <%= e.getStudent().getEmail()%>
                        </div>
                        <div class="user-date" style="font-size: 13px; color: #888; font-style: italic;">
                            <i class="fa-solid fa-calendar-days"></i> Ngày đăng ký: <%= e.getRegisterDate()%>
                        </div>
                    </div>

                    <div class="action-buttons">
                        <form action="ProcessEnrollmentController" method="post" style="margin: 0;">
                            <input type="hidden" value="<%= e.getEnrollmentID()%>" name="enrollment_id" />

                            <button type="submit" name="action" value="accept" class="btn-save">
                                Accept
                            </button>

                            <button type="submit" name="action" value="reject" class="btn-delete">
                                Reject
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
        <!--    Hien thi thong bao khi bam nut    -->
        <%
            // Lấy thông báo từ Controller gửi sang
            String msg = (String) request.getAttribute("NOTI_AD");

            // Nếu có thông báo (Khác null) và không rỗng
            if (msg != null && !msg.isEmpty()) {
        %>
        <script>
            // Lệnh này sẽ bật một cửa sổ thông báo nhỏ trên trình duyệt
            alert("<%= msg%>");
        </script>
        <%
            }
        %>
        <script>
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
