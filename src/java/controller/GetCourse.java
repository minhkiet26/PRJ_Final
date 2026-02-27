/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CourseService;
import entities.Course;

/**
 *
 * @author Admin
 */
public class GetCourse extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            String CourseID = request.getParameter("courseid");//lấy id cần           
            CourseService d = new CourseService();
            Course c = d.getCourse(CourseID);
            //lưu vào req
            request.setAttribute("COURSE_OBJ", c);
            
            String source = request.getParameter("source");
            //bien la co
            boolean showCacelBtn = false;
            
            //Chỉ khi nào có tín hiệu "mycourses" thì mới bật nút Hủy
            if("mycourses".equals(source)){
                showCacelBtn = true;
            }
            
            //Gửi request biến này sang JSP
            request.setAttribute("SHOW_CANCEL_BTN", showCacelBtn);
            
            //đẩy lên CourseDetails để in kết quả
            request.getRequestDispatcher("CourseDetail.jsp").forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
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
