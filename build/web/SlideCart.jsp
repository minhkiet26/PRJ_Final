

<%@page import="entities.Course"%>
<%@page import="java.util.ArrayList"%>
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
        <link rel="stylesheet" href="card-style.css"/>
    </head>
    <body>
        <div class="splide" role="group" aria-label="Splide Basic HTML Example">
            <div class="splide__track">
                <ul class="splide__list">
                    <%
                        //lấy list từ request
                        ArrayList<Course> courses = (ArrayList) request.getAttribute("LIST_COURSE");
                        
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
                                out.print("<a href='login.jsp'" + c.getCourseID() + "' class='course-link-wrapper'>");

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

        <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@4.1.4/dist/js/splide.min.js"></script>
        <script src="Cart.js"></script>
    </body>
</html>
