/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.util.ArrayList;
import entities.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import mylib.DBUtils;

/**
 *
 * @author Admin
 */
public class CourseService {

    public ArrayList<Course> getAllCourse() {
        ArrayList<Course> list = new ArrayList<>();
        Connection cn = null;
        try {
            //Kết nối DB
            cn = DBUtils.getConnection();
            //Kiểm tra và lấy từ DB về
            if (cn != null) {
                //viết câu SQL gửi lên DB
                String sql = "SELECT [CourseID]\n"
                        + "      ,[CourseName]\n"
                        + "      ,[Description]\n"
                        + "      ,[TuitionFee]\n"
                        + "      ,[TotalLectures]\n"
                        + "      ,[StartDate]\n"
                        + "      ,[Schedule]\n"
                        + "      ,[StudyTime]\n"
                        + "      ,[ImageURL]\n"
                        + "      ,[TeacherID]\n"
                        + "      ,[NumberEnrolled]\n"
                        + "  FROM [OnlineCourseDB].[dbo].[Course]";
                //chuẩn bị để gửi xuống SQL
                Statement st = cn.createStatement();
                //Gửi câu SQL đi và Lưu dữ liệu từ Db vào table
                ResultSet table = st.executeQuery(sql);
                //đọc table lấy từng thành phần, tạo obj, lưu vào list gửi lên trên
                if (table != null) {
                    while (table.next()) {
                        int CourseID = table.getInt("CourseID");
                        String CourseName = table.getString("CourseName");
                        String Description = table.getString("Description");
                        String TuitionFee = table.getString("TuitionFee");
                        String TotalLectures = table.getString("TotalLectures");
                        String StartDate = table.getString("StartDate");
                        String Schedule = table.getString("Schedule");
                        String StudyTime = table.getString("StudyTime");
                        String ImageURL = table.getString("ImageURL");
                        String TeacherID = table.getString("TeacherID");
                       int NumberEnrolled = table.getInt("NumberEnrolled");

                        Course c = new Course(CourseID, CourseName, Description, TuitionFee, TeacherID, ImageURL, StudyTime, Schedule, StartDate, TotalLectures, NumberEnrolled);
                        list.add(c);
                    }
                }
            }
        } catch (Exception e) {

        }
        return list;
    }

    public Course getCourse(String CourseID) {
        Connection cn = null;
        Course c = null;
        try {
            cn = DBUtils.getConnection();
            String sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                    + "SELECT [CourseID]\n"
                    + "      ,[CourseName]\n"
                    + "      ,[Description]\n"
                    + "      ,[TuitionFee]\n"
                    + "      ,[TotalLectures]\n"
                    + "      ,[StartDate]\n"
                    + "      ,[Schedule]\n"
                    + "      ,[StudyTime]\n"
                    + "      ,[ImageURL]\n"
                    + "      ,[TeacherID]\n"
                    + "      ,[NumberEnrolled]\n"
                    + "  FROM [OnlineCourseDB].[dbo].[Course]\n"
                    + "  where CourseID=?"; //Câu sql để gửi xuống
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, CourseID);//truyền tham số vào
            ResultSet table = st.executeQuery();//lưu bảng lấy được
            if (table != null) {
                while (table.next()) {
                    int courseID = table.getInt("CourseID");
                    String CourseName = table.getString("CourseName");
                    String Description = table.getString("Description");
                    String TuitionFee = table.getString("TuitionFee");
                    String TotalLectures = table.getString("TotalLectures");
                    String StartDate = table.getString("StartDate");
                    String Schedule = table.getString("Schedule");
                    String StudyTime = table.getString("StudyTime");
                    String ImageURL = table.getString("ImageURL");
                    String TeacherID = table.getString("TeacherID");
                    int NumberEnrolled = table.getInt("NumberEnrolled");
                    c = new Course(courseID, CourseName, Description, TuitionFee, TeacherID, ImageURL, StudyTime, Schedule, StartDate, TotalLectures, NumberEnrolled);
                }
            }
        } catch (Exception e) {
        }

        return c;
    }
}
