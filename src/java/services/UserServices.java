/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Student;
import entities.Teacher;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import mylib.DBUtils;

public class UserServices {

    public String getUser(String email, String password) {
        Connection cn = null;
        String role = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select [Role]\n"
                        + "from [dbo].[User]\n"
                        + "where Email = ? and Password = ?";
                //chuẩn bị cho câu sql gửi đi
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                //gửi và lưu kết quả vào biến table
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String Role = table.getString("Role");
                        role = Role;
                    }
                }
            }
        } catch (Exception e) {

        }
        return role;
    }

    public Student getStudent(String email) {
        Connection cn = null;
        Student s = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [StudentID]\n"
                        + "      ,[UserEmail]\n"
                        + "      ,[Name]\n"
                        + "      ,u.PhoneNumber\n"
                        + "  FROM Student s, dbo.[User] u\n"
                        + "  WHERE s.UserEmail = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String StudentID = table.getString("StudentID");
                        String UserEmail = table.getString("UserEmail");
                        String Name = table.getString("Name");
                        String PhoneNumber = table.getString("PhoneNumber");
                        s = new Student(StudentID, Name);
                    }
                }
            }
        } catch (Exception e) {

        }
        return s;
    }

    public Teacher getTeacher(String email) {
        Connection cn = null;
        Teacher t = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [TeacherID]\n"
                        + "      ,[UserEmail]\n"
                        + "      ,[Name]\n"
                        + "      ,u.PhoneNumber\n"
                        + "  FROM Teacher t, dbo.[User] u\n"
                        + "  WHERE t.UserEmail = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String TeacherID = table.getString("TeacherID");
                        String UserEmail = table.getString("UserEmail");
                        String Name = table.getString("Name");
                        String PhoneNumber = table.getString("PhoneNumber");
                        t = new Teacher(TeacherID, Name);
                    }
                }
            }
        } catch (Exception e) {

        }
        return t;
    }
}
