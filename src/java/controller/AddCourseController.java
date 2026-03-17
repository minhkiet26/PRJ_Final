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
import services.CourseService;

/**
 *
 * @author Admin
 */
@WebServlet(name = "AddCourseController", urlPatterns = {"/AddCourseController"})
public class AddCourseController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String courseName = request.getParameter("CourseName");
            String teacherID = request.getParameter("TeacherID");
            String studyTime = request.getParameter("StudyTime");
            String schedule = request.getParameter("Schedule");
            String startDateStr = request.getParameter("StartDate");
//            String Status = request.getParameter("enrollmentStatus");
            String imageURL = request.getParameter("ImageURL");
            String description = request.getParameter("Description");
            String tuitionFeeStr = request.getParameter("TuitionFee");
            String totalLecturesStr = request.getParameter("TotalLectures");
            String numberEnrolledStr = request.getParameter("NumberEnrolled");

            CourseService cs = new CourseService();
            if (cs.AddCourse(courseName, teacherID, studyTime, schedule, startDateStr, imageURL, description, tuitionFeeStr, totalLecturesStr, numberEnrolledStr)) {
                request.setAttribute("STATUS", "Thêm khóa học thành công!");
                request.getRequestDispatcher("CourseManagerController").forward(request, response);
                return;
            } else {
                request.setAttribute("STATUS", "Lỗi: Không thể thêm khóa học. Vui lòng kiểm tra lại!");
                request.getRequestDispatcher("CourseManagerController").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("STATUS", "Đã xảy ra lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("CourseManagerController").forward(request, response);
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
