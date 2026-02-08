/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entities.Student;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserServices;

/**
 *
 * @author ACER
 */
@WebServlet(name = "UpdateStudentInfor", urlPatterns = {"/UpdateStudentInfor"})
public class UpdateStudentInfor extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            //lay student ra
            HttpSession session = request.getSession();
            Student student = (Student) session.getAttribute("LOGIN_USER");
            if (student == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String studentID = student.getStudentID();
            String name = request.getParameter("newName");
            String newPass = request.getParameter("newPass");
            String confirmPass = request.getParameter("confirmPass");
            String phone = request.getParameter("newPhone");

            if (newPass == null || newPass.trim().isEmpty()
                    || name == null || name.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()) {

                request.setAttribute("ERROR", "Vui lòng nhập đầy đủ thông tin!");
                request.getRequestDispatcher("accountSetting.jsp").forward(request, response);
                return;
            }

            // Sau đó mới kiểm tra khớp mật khẩu
            if (!newPass.equals(confirmPass)) {
                request.setAttribute("ERROR", "Mật khẩu xác nhận không trùng khớp!");
                request.getRequestDispatcher("accountSetting.jsp").forward(request, response);
                return;
            }

            //goi xuong service
            UserServices u = new UserServices();
            boolean noti = u.updateStudent(studentID, newPass, phone, name);

            if (noti) {
                request.setAttribute("UPDATE_SUCCESSFULL", "Cập nhật thành công!");
                student.setName(name);
                student.setPassword(newPass);
                student.setPhoneNumber(phone);
                session.setAttribute("LOGIN_USER", student);
            } else {
                request.setAttribute("UPDATE_FAIL", "Cập nhật thất bại");
            }

            //tro lai trang nay
            request.getRequestDispatcher("accountSetting.jsp").forward(request, response);
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
