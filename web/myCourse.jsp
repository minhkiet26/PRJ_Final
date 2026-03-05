<%-- 
    Document   : myCourse
    Created on : Feb 3, 2026, 8:28:18 PM
    Author     : ACER
--%>

<%@page import="entities.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entities.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/css/splide.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link rel="stylesheet" href="taskBar.css"/>
        <link rel="stylesheet" href="my-card-style.css"/>
    </head>
    <body>

        <%
            // Lấy User từ Session 
            Student student = (Student) session.getAttribute("LOGIN_USER");
        %>
        <jsp:include page="taskBar.jsp"/>
        <h1 style="text-align: center; margin: 40px; color: #003366;">Khóa học đã đăng ký</h1>
        <div class="splide" role="group" aria-label="Splide Basic HTML Example">
            <div class="splide__track">
                <ul class="splide__list">
                    <%
                        //lấy list từ request
                        ArrayList<Course> courses = (ArrayList) request.getAttribute("MY_LIST_COURSE");

                        if (courses != null) {
                            for (Course c : courses) {
                                String imgSrc = c.getImageURL();
                                if (imgSrc != null && !imgSrc.startsWith("http")) {
                                    imgSrc = "images/" + imgSrc;
                                }

                                // Bắt đầu thẻ li
                                out.print("<li class='splide__slide'>");
                                out.print("<div class='course-item'>");

                                // Thẻ A bao quanh toàn bộ (để làm Flex container)
                                out.print("<a href='GetCourse?courseid=" + c.getCourseID() + "&source=mycourses' class='course-link-wrapper'>");

                                // 1. CỘT TRÁI: Hình ảnh
                                out.print("<div class='course-thumb'>");
                                out.print("<img src='" + imgSrc + "' alt='" + c.getCourseName() + "' />");
                                out.print("</div>");

                                // 2. CỘT PHẢI: Nội dung
                                out.print("<div class='course-content'>");

                                // --- QUAN TRỌNG: Thêm div 'course-top-info' bao bọc phần trên ---
                                out.print("<div class='course-top-info'>");

                                // Tên khóa học
                                out.print("<h3 class='course-name'>" + c.getCourseName() + "</h3>");

                                // Danh sách thông tin 
                                out.print("<div class='course-meta-list'>");
                                out.print("<p><i class='fa-solid fa-book'></i> " + c.getTotalLectures() + " Bài giảng</p>");
                                out.print("<p><i class='fa-regular fa-calendar-check'></i> Khai giảng: " + c.getStartDate() + "</p>");
                                out.print("<p><i class='fa-regular fa-calendar-days'></i> Lịch học: " + c.getSchedule() + "</p>");
                                out.print("<p><i class='fa-regular fa-clock'></i> Giờ học: " + c.getStudyTime() + "</p>");
                                out.print("</div>"); // Kết thúc course-meta-list

                                out.print("</div>"); // Kết thúc course-top-info (QUAN TRỌNG)
                                // ---------------------------------------------------------------

                                out.print("<div class='course-bottom'>");
                                String cssClass = "";
                                String label = "";

                                if ("Approved".equals(c.getEnrollmentStatus())) {
                                    cssClass = "status-approved"; // Class màu xanh
                                    label = "Đã duyệt";
                                } else if ("Rejected".equals(c.getEnrollmentStatus())) {
                                    cssClass = "status-rejected"; // Class màu đỏ
                                    label = "Từ chối đăng ký";
                                } else if ("Canceled".equals(c.getEnrollmentStatus())) {
                                    cssClass = "status-canceled"; //Class mau xam
                                    label = "Đã hủy";
                                } else {
                                    cssClass = "status-pending"; // Class màu vàng
                                    label = "Đang chờ đăng ký";
                                }
                                out.print("<div class='status-box'>");
                                out.print("<span class='status-badge " + cssClass + "'>");
                                out.print("<i class='fa-solid fa-circle-info'></i> " + label);
                                out.print("</span>");
                                out.print("</div>");
                                out.print("</div>"); // Kết thúc course-bottom

                                out.print("</div>"); // Kết thúc course-content
                                out.print("</a>"); // Kết thúc thẻ A
                                out.print("</div>"); // Kết thúc course-item
                                out.print("</li>"); // Kết thúc li

                            }
                        }
                    %> 

                </ul>
            </div>
        </div> 


        <!--    Hien thi thong bao khi bam nut dang ky mon hoc-->
        <%
            // Lấy thông báo từ Controller gửi sang
            String msg = (String) request.getAttribute("NOTI");

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

        <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js"></script>
        <script src="MyCart.js"></script>
    </body>
</html>
