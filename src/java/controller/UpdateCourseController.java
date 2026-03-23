/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CourseService;
import entities.Course;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateCourseController", urlPatterns = {"/UpdateCourseController"})
public class UpdateCourseController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            // 2. Lấy dữ liệu từ các thẻ input (Dựa vào thuộc tính name="...")
            String courseID = request.getParameter("CourseID");
            String courseName = request.getParameter("CourseName");
            String tuitionFee = request.getParameter("TuitionFee");
            String teacherID = request.getParameter("TeacherID");
            String studyTime = request.getParameter("StudyTime");
            String schedule = request.getParameter("Schedule");
            String startDate = request.getParameter("StartDate");
            String totalLectures = request.getParameter("TotalLectures");
            String numberEnrolled = request.getParameter("NumberEnrolled");
            String status = request.getParameter("Status");
            String imageURL = request.getParameter("ImageURL");
            String description = request.getParameter("Description");

            // 3. Đóng gói dữ liệu vào Entity (Tất cả thuộc tính trong Course.java của bạn đều là String)
            Course courseUpdate = new Course(courseID, courseName, description, tuitionFee, teacherID, 
                                             imageURL, studyTime, schedule, startDate, 
                                             totalLectures, numberEnrolled, status, null);

            // 4. Gọi tầng Service để thực hiện truy vấn Database
            CourseService service = new CourseService();
            boolean isSuccess = service.updateCourse(courseUpdate);

            // 5. Xử lý kết quả trả về và chuyển hướng trang
            HttpSession session = request.getSession();
            if (isSuccess) {
                // Set biến STATUS để file .jsp bắt được và hiện alert()
                session.setAttribute("STATUS", "Cập nhật khóa học thành công!");
            } else {
session.setAttribute("STATUS", "Cập nhật thất bại. Không tìm thấy khóa học hoặc lỗi DB!");
            }
            
            // Chuyển hướng về lại trang quản lý khóa học (Dựa vào danh sách file của bạn, tôi dự đoán là CourseManagerController)
            response.sendRedirect("CourseManagerController");

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi ra console để debug
            HttpSession session = request.getSession();
            session.setAttribute("STATUS", "Lỗi hệ thống: " + e.getMessage());
            response.sendRedirect("CourseManagerController");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
