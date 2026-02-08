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
                                // ---------------------------------------------------

                                out.print("<li class='splide__slide'>");
                                out.print("<div class='course-item'>"); // Class bọc ngoài

                                // Thẻ A bao quanh
                                out.print("<a href='GetCourse?courseid=" + c.getCourseID() + "&source=mycourses' class='course-link-wrapper'>");

                                //Hình ảnh
                                out.print("<div class='course-thumb'>");
                                out.print("<img src='" + imgSrc + "' alt='" + c.getCourseName() + "' />");
                                out.print("</div>");

                                //Nội dung
                                out.print("<div class='course-content'>");

                                // Tên khóa học
                                out.print("<h3 class='course-name'>" + c.getCourseName() + "</h3>");

                                // Danh sách thông tin 
                                out.print("<div class='course-meta-list'>");
                                out.print("<p><i class='fa-solid fa-book'></i> " + c.getTotalLectures() + " Bài giảng</p>");
                                out.print("<p><i class='fa-regular fa-calendar-check'></i> Khai giảng: " + c.getStartDate() + "</p>");
                                out.print("<p><i class='fa-regular fa-calendar-days'></i> Lịch học: " + c.getSchedule() + "</p>");
                                out.print("<p><i class='fa-regular fa-clock'></i> Giờ học: " + c.getStudyTime() + "</p>");
                                out.print("</div>");

                                // Footer
                                out.print("<div class='course-bottom'>");

                                // Giá tiền
                                out.print("<div class='price-box'>");
                                out.print("<span class='current-price'>" + c.getTuitionFee() + "đ</span>");
                                out.print("</div>");
                                out.print("</div>");

                                out.print("</div>");
                                out.print("</a>");
                                out.print("</div>");
                                out.print("</li>");

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
