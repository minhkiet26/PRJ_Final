
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
                String sql = "SELECT * FROM Course";

                //chuẩn bị để gửi xuống SQL
                Statement st = cn.createStatement();
                //Gửi câu SQL đi và Lưu dữ liệu từ Db vào table
                ResultSet table = st.executeQuery(sql);
                //đọc table lấy từng thành phần, tạo obj, lưu vào list gửi lên trên
                if (table != null) {
                    while (table.next()) {
                        String CourseID = table.getString("CourseID");
                        String CourseName = table.getString("CourseName");
                        String Description = table.getString("Description");
                        String TuitionFee = table.getString("TuitionFee");
                        String TotalLectures = table.getString("TotalLectures");
                        String StartDate = table.getString("StartDate");
                        String Schedule = table.getString("Schedule");
                        String StudyTime = table.getString("StudyTime");
                        String ImageURL = table.getString("ImageURL");
                        String TeacherID = table.getString("TeacherID");
                        String NumberEnrolled = table.getString("NumberEnrolled");
                        String Status = table.getString("Status");

                        Course c = new Course(CourseID, CourseName, Description, TuitionFee, TeacherID, ImageURL, StudyTime, Schedule, StartDate, TotalLectures, NumberEnrolled, Status);
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
            String sql = "SELECT *"
                    + "FROM Course \n"
                    + "WHERE CourseID = ?"; //Câu sql để gửi xuống

            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, CourseID);//truyền tham số vào
            ResultSet table = st.executeQuery();//lưu bảng lấy được
            if (table != null) {
                while (table.next()) {
                    String courseID = table.getString("CourseID");
                    String CourseName = table.getString("CourseName");
                    String Description = table.getString("Description");
                    String TuitionFee = table.getString("TuitionFee");
                    String TotalLectures = table.getString("TotalLectures");
                    String StartDate = table.getString("StartDate");
                    String Schedule = table.getString("Schedule");
                    String StudyTime = table.getString("StudyTime");
                    String ImageURL = table.getString("ImageURL");
                    String TeacherID = table.getString("TeacherID");
                    String NumberEnrolled = table.getString("NumberEnrolled");
                    String Status = table.getString("Status");

                    c = new Course(courseID, CourseName, Description, TuitionFee, TeacherID, ImageURL, StudyTime, Schedule, StartDate, TotalLectures, NumberEnrolled, Status);
                }
            }
        } catch (Exception e) {
        }

        return c;
    }

    public ArrayList<Course> listOfRegisteredCourse(String studentID) {
        ArrayList<Course> list = new ArrayList<>();
        Connection cn = null;

        try {
            //Ket noi DB
            cn = DBUtils.getConnection();
            //Kiem tra va lay du lieu tu DB ve
            if (cn != null) {
                //Viet cau SQL gui len DB
                String sql = "SELECT C.*, E.Status AS EnrollmentStatus \n"
                        + "FROM [dbo].[Course] C \n"
                        + "JOIN [dbo].[Enrollment] E ON C.CourseID = E.CourseID \n"
                        + "WHERE E.StudentID = ?";

                //chuan bi de gui xuong SQL
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, studentID);//truyền tham số vào
                //Gửi câu SQL đi và Lưu dữ liệu từ Db vào table
                ResultSet table = st.executeQuery();
                //đọc table lấy từng thành phần, tạo obj, lưu vào list gửi lên trên
                if (table != null) {
                    while (table.next()) {
                        String CourseID = table.getString("CourseID");
                        String CourseName = table.getString("CourseName");
                        String Description = table.getString("Description");
                        String TuitionFee = table.getString("TuitionFee");
                        String TotalLectures = table.getString("TotalLectures");
                        String StartDate = table.getString("StartDate");
                        String Schedule = table.getString("Schedule");
                        String StudyTime = table.getString("StudyTime");
                        String ImageURL = table.getString("ImageURL");
                        String TeacherID = table.getString("TeacherID");
                        String NumberEnrolled = table.getString("NumberEnrolled");
                        String Status = table.getString("Status");

                        Course c = new Course(CourseID, CourseName, Description, TuitionFee, TeacherID, ImageURL, StudyTime, Schedule, StartDate, TotalLectures, NumberEnrolled, Status);
                        list.add(c);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String cancelCourse(String studentID, String courseID) {
        Connection cn = null;
        try {
            //ket noi DB
            cn = DBUtils.getConnection();
            if (cn != null) {
                //viet cau SQL
                String sql = "UPDATE Enrollment \n"
                        + "SET Status = 'Canceled', CancelDate = GETDATE() \n"
                        + "WHERE StudentID = ? AND CourseID = ?";
                //chuan bi de gui xuong DB
                PreparedStatement st = cn.prepareStatement(sql);
                //truyen du lieu
                st.setString(1, studentID);
                st.setString(2, courseID);
                //gui cau SQL di luu du lieu tu DB 
                st.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hủy đăng ký khóa học thành công!!";
    }

    public boolean DeleteCourseById(String userID) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Course]\n"
                        + "   SET\n"
                        + "     [Status] = 'Close'\n"
                        + " WHERE CourseID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, userID);
                int check = st.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean OpenCourseById(String userID) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE [dbo].[Course]\n"
                        + "   SET\n"
                        + "     [Status] = 'Open'\n"
                        + " WHERE CourseID = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, userID);
                int check = st.executeUpdate();
                if (check > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean AddCourse(String courseName, String teacherID, String studyTime,
            String schedule, String startDateStr,
            String imageURL, String description, String tuitionFeeStr,
            String totalLecturesStr, String numberEnrolledStr) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO [dbo].[Course] ([CourseName], [Description], [TuitionFee], [TotalLectures], [StartDate], [Schedule], [StudyTime], [ImageURL], [NumberEnrolled], [TeacherID]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);

                st.setString(1, courseName);

                st.setString(2, description);

                st.setDouble(3, Double.parseDouble(tuitionFeeStr));

                st.setInt(4, Integer.parseInt(totalLecturesStr));

                st.setString(5, startDateStr);

                st.setString(6, schedule);

                st.setString(7, studyTime);

                st.setString(8, imageURL);

                st.setInt(9, Integer.parseInt(numberEnrolledStr));

                st.setInt(10, Integer.parseInt(teacherID));

                int result = st.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Course> SearchCourse(String name) {
        Connection cn = null;
        ArrayList<Course> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT [CourseID]\n"
                        + "      ,[CourseName]\n"
                        + "      ,[Description]\n"
                        + "      ,[TuitionFee]\n"
                        + "      ,[TotalLectures]\n"
                        + "      ,[StartDate]\n"
                        + "      ,[Schedule]\n"
                        + "      ,[StudyTime]\n"
                        + "      ,[ImageURL]\n"
                        + "      ,[NumberEnrolled]\n"
                        + "      ,[TeacherID]\n"
                        + "      ,[Status]\n"
                        + "FROM [dbo].[Course]\n"
                        + "WHERE [CourseName] LIKE ?;";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + name + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String CourseID = table.getString("CourseID");
                        String CourseName = table.getString("CourseName");
                        String Description = table.getString("Description");
                        String TuitionFee = table.getString("TuitionFee");
                        String TotalLectures = table.getString("TotalLectures");
                        String StartDate = table.getString("StartDate");
                        String Schedule = table.getString("Schedule");
                        String StudyTime = table.getString("StudyTime");
                        String ImageURL = table.getString("ImageURL");
                        String TeacherID = table.getString("TeacherID");
                        String NumberEnrolled = table.getString("NumberEnrolled");
                        String Status = table.getString("Status");
                        list.add(new Course(CourseID, CourseName, Description, TuitionFee, TeacherID, ImageURL, StudyTime, Schedule, StartDate, TotalLectures, NumberEnrolled, Status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
