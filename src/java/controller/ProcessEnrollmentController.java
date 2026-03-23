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
import services.EnrollmentService;

/**
 *
 * @author ACER
 */
@WebServlet(name = "ProcessEnrollmentController", urlPatterns = {"/ProcessEnrollmentController"})
public class ProcessEnrollmentController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String id = request.getParameter("enrollment_id");
            EnrollmentService es = new EnrollmentService();
            
            HttpSession session = request.getSession();
            String action = request.getParameter("action");
            String noti = "";
            if("accept".equals(action)){
                noti = es.approveStudent(id);
            }else{
                noti = es.rejectStudent(id);
            }
            session.setAttribute("NOTI_AD", noti);
            //không nên dùng forward boi vì:
//            Nếu Admin bấm Accept xong, 
//            sau đó lỡ tay nhấn F5 (Refresh) trình duyệt, 
//            trình duyệt sẽ hỏi "Bạn có muốn gửi lại biểu mẫu không?". 
//            Nếu Admin bấm Có, lệnh Accept sẽ bị gửi đi một lần nữa.
//nếu dùng Redirect thì bạn phải lưu thông báo vào session thay vì request để nó không bị mất.
            response.sendRedirect("ViewPendingStudentsController");
        } catch (Exception e) {
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
