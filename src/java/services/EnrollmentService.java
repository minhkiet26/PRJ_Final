/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mylib.DBUtils;

/**
 *
 * @author ACER
 */
public class EnrollmentService {
    public String registerCourse(String studentID, String courseID){
        Connection cn = null;
        try{
            //Ket noi DB
            cn = DBUtils.getConnection();
            //Kiem tra va lay tu DB ve
            if(cn != null){
                //KHÓA HỌC CÓ TỒN TẠI HAY BỊ XÓA KHÔNG
                String checksql1 = "select Schedule \n" +
                                "from Course\n" +
                                "where CourseID = ? ";
                PreparedStatement stCheck = cn.prepareStatement(checksql1);
                stCheck.setString(1, courseID);
                ResultSet table = stCheck.executeQuery();
                if(table.next()){
                    String schedule = table.getString("Schedule");
                    
                    if(schedule != null && schedule.startsWith("STOP_")){
                        return "Khóa học này đã bị đóng!";
                    }
                }else{
                    return "Khóa học không tồn tại!";
                }
                
                //SINH VIÊN ĐÃ ĐĂNG KÝ MÔN NÀY CHƯA
                String checksql2 = "select * \n" +
                                    "from Enrollment\n" +
                                    "where StudentID = ? and CourseID = ?";
                PreparedStatement stCheck2 = cn.prepareStatement(checksql2);
                stCheck2.setString(1, studentID);
                stCheck2.setString(2, courseID);
                ResultSet table2 = stCheck2.executeQuery();
                if(table2.next()){
                    //Neu tim thay du lieu => da dang ky mon nay roi
                    return "Bạn đã đăng ký khóa học này rồi, vui lòng chờ duyệt!";
                }
                
                //THỰC HIỆN INSERT
                //Viet cau sql gui len DB
                String sql = "INSERT INTO Enrollment (StudentID, CourseID, Status, RegisterDate) \n" +
                            "VALUES (?, ?, 'Pending', GETDATE());";
                //chuan bi de gui xuong sql
                PreparedStatement stInsert = cn.prepareStatement(sql);
                stInsert.setString(1, studentID);
                stInsert.setString(2, courseID);
                //Gui cau sql di va luu du lieu tu DB vao table
                //Chi dung cho nhung hanh dong "Thay doi du lieu": Insert, Update, Delete
                int check = stInsert.executeUpdate();     
                if(check > 0) return "Đăng ký thành công! Vui lòng chờ Admin duyệt.";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Lỗi hệ thống, vui lòng thử lại sau!";
    }
}
