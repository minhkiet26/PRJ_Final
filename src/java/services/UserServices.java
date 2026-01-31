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
import sun.security.util.Password;

public class UserServices {

    public User getUser(String email, String password) {
        Connection cn = null;
        User u = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [Email]\n"
                        + "      ,[Password]\n"
                        + "      ,[Role]\n"
                        + "      ,[PhoneNumber]\n"
                        + "  FROM [OnlineCourseDB].[dbo].[User]\n"
                        + "  WHERE Email = ? and Password = ?";
                //chuẩn bị cho câu sql gửi đi
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                st.setString(2, password);
                //gửi và lưu kết quả vào biến table
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String Email = table.getString("Email");
                        String Password = table.getString("Password");
                        String Role = table.getString("Role");
                        String PhoneNumber = table.getString("PhoneNumber");
                        u = new User(Email, Password, PhoneNumber, Role);
                    }
                }
            }
        } catch (Exception e) {

        }
        return u;
    }

    public Student getStudent(String email) {
        Connection cn = null;
        Student s = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [s].*, [u].*\n"
                        + "FROM [Student] AS [s]\n"
                        + "JOIN [User] AS [u] ON [s].[UserEmail] = [u].[Email]\n"
                        + "WHERE [s].[UserEmail] = ?;";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String StudentID = table.getString("StudentID");
                        String UserEmail = table.getString("UserEmail");
                        String Name = table.getString("Name");
                        String PhoneNumber = table.getString("PhoneNumber");
                        String Password = table.getString("Password");
                        String Role = table.getString("Role");
                        s = new Student(email, Password, PhoneNumber, Role, StudentID, Name);
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
                String sql = "SELECT [t].*, [u].*\n"
                        + "FROM [Teacher] AS [t]\n"
                        + "JOIN [User] AS [u] ON [t].[UserEmail] = [u].[Email]\n"
                        + "WHERE [t].[UserEmail] = ?;";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, email);
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String TeacherID = table.getString("TeacherID");
                        String UserEmail = table.getString("UserEmail");
                        String Name = table.getString("Name");
                        String PhoneNumber = table.getString("PhoneNumber");
                        String Password = table.getString("Password");
                        String Role = table.getString("Role");
                        t = new Teacher(email, Password, PhoneNumber, Role, TeacherID, Name);
                    }
                }
            }
        } catch (Exception e) {

        }
        return t;
    }
}
