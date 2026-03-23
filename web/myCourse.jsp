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
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<c:if test="${sessionScope.LOGIN_USER == null}">
    <c:redirect url="login.jsp"/>
</c:if>
<c:if test="${sessionScope.LOGIN_USER != null}">
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
            <jsp:include page="taskBar.jsp"/>

            <h1 style="text-align: center; margin: 40px; color: #003366;">Khóa học đã đăng ký</h1>

            <div class="course-vertical-container">
                <%
                    ArrayList<Course> courses = (ArrayList) request.getAttribute("MY_LIST_COURSE");
                    boolean hasVisibleCourse = false;
                    if (courses != null && !courses.isEmpty()) {
                        for (Course c : courses) {
                            if (!"Close".equals(c.getStatus())) {
                                hasVisibleCourse = true;
                                String imgSrc = c.getImageURL();
                                if (imgSrc != null && !imgSrc.startsWith("http")) {
                                    imgSrc = "images/" + imgSrc;
                                }
                                // Bắt đầu một Item khóa học
                                out.print("<div class='course-item-vertical'>");
                                out.print("<a href='GetCourse?courseid=" + c.getCourseID() + "&source=mycourses' class='course-link-wrapper'>");

                                // 1. CỘT TRÁI: Hình ảnh
                                out.print("<div class='course-thumb'>");
                                out.print("<img src='" + imgSrc + "' alt='" + c.getCourseName() + "' />");
                                out.print("</div>");

                                // 2. CỘT PHẢI: Nội dung
                                out.print("<div class='course-content'>");
                                out.print("<div class='course-top-info'>");
                                out.print("<h3 class='course-name'>" + c.getCourseName() + "</h3>");
                                out.print("<div class='course-meta-list'>");
                                out.print("<p><i class='fa-solid fa-book'></i> " + c.getTotalLectures() + " Bài giảng</p>");
                                out.print("<p><i class='fa-regular fa-calendar-check'></i> Khai giảng: " + c.getStartDate() + "</p>");
                                out.print("<p><i class='fa-regular fa-calendar-days'></i> Lịch học: " + c.getSchedule() + "</p>");
                                out.print("<p><i class='fa-regular fa-clock'></i> Giờ học: " + c.getStudyTime() + "</p>");
                                out.print("</div>");
                                out.print("</div>");

                                out.print("<div class='course-bottom'>");
                                String cssClass = "";
                                String label = "";

                                if ("Approved".equals(c.getEnrollmentStatus())) {
                                    cssClass = "status-approved";
                                    label = "Đã duyệt";
                                } else if ("Rejected".equals(c.getEnrollmentStatus())) {
                                    cssClass = "status-rejected";
                                    label = "Từ chối đăng ký";
                                } else if ("Canceled".equals(c.getEnrollmentStatus())) {
                                    cssClass = "status-canceled";
                                    label = "Đã hủy";
                                } else {
                                    cssClass = "status-pending";
                                    label = "Đang chờ đăng ký";
                                }
                                out.print("<div class='status-box'>");
                                out.print("<span class='status-badge " + cssClass + "'>");
                                out.print("<i class='fa-solid fa-circle-info'></i> " + label);
                                out.print("</span>");
                                out.print("</div>");
                                out.print("</div>");
                                out.print("</div>");
                                out.print("</a>");
                                out.print("</div>");
                            }
                        }
                    }
                    if (!hasVisibleCourse) {
                        out.print("<p style='text-align: center; padding: 40px 20px; background-color: #f8f9fa; border: 1px dashed #dce1e6; border-radius: 12px; color: #5c6a7a; font-size: 16px; margin: 20px;'><i class='fa-solid fa-box-open' style='margin-right: 8px; color: #a1b0c0;'></i>Bạn chưa đăng ký khóa học nào.</p>");
                    }
                %> 
            </div>

            <%                String msg = (String) request.getAttribute("NOTI");
                if (msg != null && !msg.isEmpty()) {
            %>
            <script>
                alert("<%= msg%>");
            </script>
            <%
                }
            %>
        </body>
    </html>
</c:if>
