<%@page import="entities.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết khóa học</title>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    
    <link rel="stylesheet" href="course-style.css"> 
</head>
<body>

    <%
        // Lấy đối tượng Course từ request
        Course c = (Course) request.getAttribute("COURSE_OBJ");

        if (c != null) {
            String imgSrc = c.getImageURL();
            if (imgSrc != null && !imgSrc.startsWith("http")) {
                imgSrc = "images/" + imgSrc;
            }
    %>

    <div class="container">
        <div class="main-content">
            <div class="course-header">
                <div class="course-meta">Khóa học > <%= c.getTeacherID() %></div> 
                <h1 class="course-title"><%= c.getCourseName() %></h1>
            </div>

            <div class="section-box">
                <h3 class="section-title">Tổng quan khóa học</h3>
                <div class="description-text">
                    <p><%= c.getDescription() %></p>
                </div>
            </div>

            <div class="section-box">
                <h3 class="section-title">Thông tin giảng viên</h3>
                <div class="description-text">
                    <p>Giảng viên phụ trách: <strong><%= c.getTeacherID() %></strong></p>
                    <p>Đội ngũ giảng viên giàu kinh nghiệm từ 28Tech.</p>
                </div>
            </div>
        </div>

        <aside class="sidebar">
            <div class="course-card">
                <div class="card-img-wrapper">
                    <img src="<%= imgSrc %>" alt="<%= c.getCourseName() %>">
                </div>
                
                <div class="card-body">
                    <div class="price-tag"><%= c.getTuitionFee() %> đ</div>

                    <a href="RegisterController=<%= c.getCourseID() %>" class="btn-register">
                        Đăng Ký Học
                    </a>

                    <ul class="info-list">
                        <li><span class="info-label">Khai giảng</span> <span class="info-value"><%= c.getStartDate() %></span></li>
                        <li><span class="info-label">Lịch học</span> <span class="info-value"><%= c.getSchedule() %></span></li>
                        <li><span class="info-label">Giờ học</span> <span class="info-value"><%= c.getStudyTime() %></span></li>
                        <li><span class="info-label">Số bài giảng</span> <span class="info-value"><%= c.getTotalLectures() %></span></li>
                        <li><span class="info-label">Học viên</span> <span class="info-value"><%= c.getNumberEnroller()%></span></li>
                        <li><span class="info-label">Giảng viên</span> <span class="info-value"><%= c.getTeacherID() %></span></li>
                    </ul>

                    <div class="contact-box">
                        <p style="font-size: 13px; color: #666;">Cần tư vấn thêm?</p>
                        <p style="font-weight: bold; color: #0085FF;"><i class="fa-solid fa-phone"></i> Zalo: 0965303260</p>
                    </div>
                </div>
            </div>
        </aside>

    </div>

    <% 
        } else { 
    %>
        <div style="text-align: center; margin-top: 50px;">
            <h2>Không tìm thấy thông tin khóa học!</h2>
        </div>
    <% 
        } 
    %>

</body>
</html>