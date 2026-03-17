/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Enrollment;
import entities.Student;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author ACER
 */
public class EnrollmentService {

    public String registerCourse(String studentID, String courseID) {
        Connection cn = null;
        try {
            //Ket noi DB
            cn = DBUtils.getConnection();
            //Kiem tra va lay tu DB ve
            if (cn != null) {
                //SINH VIÊN ĐÃ ĐĂNG KÝ MÔN NÀY CHƯA
                String checksql = "SELECT Status FROM Enrollment \n"
                        + "WHERE StudentID = ? AND CourseID = ?";
                PreparedStatement stCheck = cn.prepareStatement(checksql);
                stCheck.setString(1, studentID);
                stCheck.setString(2, courseID);
                ResultSet table = stCheck.executeQuery();
                if (table.next()) {
                    String status = table.getString("Status");

                    if (status.equals("Pending") || status.equals("Approved")) {
                        return "Bạn đã đăng ký khóa học này rồi!";
                    } else {
                        String sqlupdate = "UPDATE Enrollment \n"
                                + "SET Status = 'Pending', RegisterDate = GETDATE(), CancelDate = NULL \n"
                                + "WHERE StudentID = ? AND CourseID = ?";
                        PreparedStatement stUpdate = cn.prepareStatement(sqlupdate);
                        stUpdate.setString(1, studentID);
                        stUpdate.setString(2, courseID);
                        int update = stUpdate.executeUpdate();
                        if (update > 0) {
                            return "Đăng ký thành công! Vui lòng chờ Admin duyệt.";
                        }
                    }
                } else {
                    //THỰC HIỆN INSERT
                    //Viet cau sql gui len DB
                    String sql = "INSERT INTO Enrollment (StudentID, CourseID, Status, RegisterDate) \n"
                            + "VALUES (?, ?, 'Pending', GETDATE());";
                    //chuan bi de gui xuong sql
                    PreparedStatement stInsert = cn.prepareStatement(sql);
                    stInsert.setString(1, studentID);
                    stInsert.setString(2, courseID);
                    //Gui cau sql di va luu du lieu tu DB vao table
                    //Chi dung cho nhung hanh dong "Thay doi du lieu": Insert, Update, Delete
                    int check = stInsert.executeUpdate();
                    if (check > 0) {
                        return "Đăng ký thành công! Vui lòng chờ Admin duyệt.";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Lỗi hệ thống, vui lòng thử lại sau!";
    }

    public ArrayList<Enrollment> getStudentOfCourse(String courseID) {
        ArrayList<Enrollment> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT e.EnrollmentID, s.UserEmail, s.Name, e.RegisterDate "
                        + "FROM Enrollment e "
                        + "JOIN Student s ON e.StudentID = s.StudentID "
                        + "WHERE e.CourseID = ? AND e.Status = 'Pending'";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, courseID);
                ResultSet table = st.executeQuery();
                while (table.next()) {
                    Enrollment e = new Enrollment();
                    e.setEnrollmentID(table.getString("EnrollmentID"));
                    e.setRegisterDate(table.getString("RegisterDate"));

                    Student s = new Student();
                    s.setEmail(table.getString("UserEmail"));
                    s.setName(table.getString("Name"));

                    e.setStudent(s);

                    list.add(e);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String approveStudent(String enrollmentID) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update Enrollment\n"
                        + "Set Status = 'Approved', UpdateAt = GETDATE()\n"
                        + "Where EnrollmentID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, enrollmentID);
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Duyệt đơn đăng ký thành công!";
    }
    
    public String rejectStudent(String enrollmentID) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "Update Enrollment\n"
                        + "Set Status = 'Rejected', UpdateAt = GETDATE()\n"
                        + "Where EnrollmentID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, enrollmentID);
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xử lý thành công! Đã từ chối đơn đăng ký của học viên.";
    }
}
