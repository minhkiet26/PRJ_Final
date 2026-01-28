/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entities.Student;
import entities.Teacher;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserServices;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            UserServices Us = new UserServices();
            String roleCheck = Us.getUser(email, password);
            if (roleCheck != null) {
                if (roleCheck == "Student") {
                    Student s = Us.getStudent(email);
                    //Lưu kết quả vào session để sau này còn sử sụng
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", s);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } else {
                    Teacher t = Us.getTeacher(email);
                    //Lưu kết quả vào session để sau này còn sử sụng
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", t);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } else {
                String error = "Wrong Email or Password";//thông báo lỗi
                request.setAttribute("ERROR", error);//đẩy vào req
                request.getRequestDispatcher("login.jsp").forward(request, response);//chuyển trang để hiển thị lên cho người dùng
            }
        } catch (Exception e) {

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
