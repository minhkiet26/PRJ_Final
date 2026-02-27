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
import java.sql.Statement;
import mylib.DBUtils;
import sun.security.util.Password;

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

    public boolean updateStudent(String studentID, String password, String phone, String name) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sqlStudent = "UPDATE Student SET Name = ? WHERE StudentID = ?";
                PreparedStatement pst1 = cn.prepareStatement(sqlStudent);
                pst1.setString(1, name);      
                pst1.setString(2, studentID);    
                pst1.executeUpdate();
                
                String sql = "UPDATE U\n"
                        + "SET \n"
                        + "    U.Password = ?,   \n"
                        + "    U.PhoneNumber = ?    \n"
                        + "FROM dbo.[User] U\n"
                        + "JOIN Student S ON U.Email = S.UserEmail\n"
                        + "WHERE S.StudentID = ?";

                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, password);
                st.setString(2, phone);
                st.setString(3, studentID);

                int check = st.executeUpdate();
                if(check > 0) return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
