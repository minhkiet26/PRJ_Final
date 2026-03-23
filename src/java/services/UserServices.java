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
import java.util.ArrayList;
import mylib.DBUtils;

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
                        + "      ,[Status]\n"
                        + "  FROM [EducationDB].[dbo].[User]\n"
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
                        String Status = table.getString("Status");
                        u = new User(Email, Password, PhoneNumber, Role, Status);
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
                        + "WHERE [s].[UserEmail] = ?";
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
                        String Status = table.getString("Status");
                        s = new Student(email, Password, PhoneNumber, Role, Status, StudentID, Name);
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
                if (check > 0) {
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateStudentV2(String email, String password, String phone, String role, String name) {
        Connection cn = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                cn.setAutoCommit(false); // Bắt đầu Transaction

                String sqlInfo = "UPDATE Student SET Name = ? WHERE UserEmail = ?";
                pst1 = cn.prepareStatement(sqlInfo);
                pst1.setString(1, name);
                pst1.setString(2, email);
                pst1.executeUpdate();

                String sqlUser = "UPDATE [User] SET Password = ?, PhoneNumber = ?, Role = ? WHERE Email = ?";
                pst2 = cn.prepareStatement(sqlUser);
                pst2.setString(1, password);
                pst2.setString(2, phone);
                pst2.setString(3, role);
                pst2.setString(4, email);
                int check = pst2.executeUpdate();

                cn.commit(); // Thành công cả hai thì mới lưu lại
                return check > 0;
            }
        } catch (Exception e) {
            if (cn != null) try {
                cn.rollback();
            } catch (Exception ex) {
            } // Lỗi thì hoàn tác
            e.printStackTrace();
        } finally {
            try {
                if (pst1 != null) {
                    pst1.close();
                }
                if (pst2 != null) {
                    pst2.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    public boolean updateTeacher(String email, String password, String phone, String role, String name) {
        Connection cn = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                // Tắt chế độ tự động lưu (Bắt đầu giao dịch)
                cn.setAutoCommit(false);

                // 1. Cập nhật tên trong bảng Teacher
                String sqlTeacher = "UPDATE Teacher SET Name = ? WHERE UserEmail = ?";
                pst1 = cn.prepareStatement(sqlTeacher);
                pst1.setString(1, name);
                pst1.setString(2, email);
                pst1.executeUpdate();

                // 2. Cập nhật thông tin trong bảng [User]
                String sqlUser = "UPDATE [User] SET Password = ?, PhoneNumber = ?, Role = ? WHERE Email = ?";
                pst2 = cn.prepareStatement(sqlUser);
                pst2.setString(1, password);
                pst2.setString(2, phone);
                pst2.setString(3, role);
                pst2.setString(4, email);

                int check = pst2.executeUpdate();

                // Nếu mọi thứ ổn, lưu các thay đổi vào DB
                cn.commit();
                return check > 0;
            }
        } catch (Exception e) {
            // Nếu có bất kỳ lỗi nào xảy ra, hủy bỏ toàn bộ quá trình (không cập nhật bảng nào hết)
            if (cn != null) {
                try {
                    cn.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Đóng tất cả các "vòi nước" để tránh rò rỉ bộ nhớ
            try {
                if (pst1 != null) {
                    pst1.close();
                }
                if (pst2 != null) {
                    pst2.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
                        + "WHERE [t].[UserEmail] = ?";
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
                        String Status = table.getString("Status");
                        t = new Teacher(email, Password, PhoneNumber, Role, Status, TeacherID, Name);
                    }
                }
            }
        } catch (Exception e) {

        }
        return t;
    }

    public Boolean postUser(String Email, String Password, String PhoneNumber, String Role) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO [User] (Email, Password, PhoneNumber, Role) VALUES (?, ?, ?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, Email);
                st.setString(2, Password);
                st.setString(3, PhoneNumber);
                st.setString(4, Role);
                st.executeUpdate();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean postStudent(String Name, String UserEmail) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO Student (Name, UserEmail) VALUES (?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, Name);
                st.setString(2, UserEmail);
                st.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean postTeacher(String Name, String UserEmail) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "INSERT INTO Teacher (Name, UserEmail) VALUES (?, ?)";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, Name);
                st.setString(2, UserEmail);
                st.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean checkEmail(String Email) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT Email FROM [User] WHERE Email = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, Email);
                ResultSet table = st.executeQuery();

                if (table.next()) {
                    return true; // Email đã tồn tại
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        Connection cn = null;
        ResultSet table = null;
        Statement st = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                //viết câu SQL gửi lên DB
                String sql = "SELECT [Email]\n"
                        + "      ,[Password]\n"
                        + "      ,[Role]\n"
                        + "      ,[PhoneNumber]\n"
                        + "      ,[Status]\n"
                        + "FROM [EducationDB].[dbo].[User]";
                //chuẩn bị để gửi xuống SQL
                st = cn.createStatement();
                //Gửi câu SQL đi và Lưu dữ liệu từ Db vào table
                table = st.executeQuery(sql);
                //đọc table lấy từng thành phần, tạo obj, lưu vào list gửi lên trên
                if (table != null) {
                    while (table.next()) {
                        String Email = table.getString("Email");
//                        String Name = table.getString("Name");
                        String Password = table.getString("Password");
                        String Role = table.getString("Role");
                        String PhoneNumber = table.getString("PhoneNumber");
                        String Status = table.getString("Status");
                        User u = new User(Email, Password, PhoneNumber, Role, Status);
                        list.add(u);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (table != null) {
                    table.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public ArrayList<Student> getAllStudent() {
        ArrayList<Student> list = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet table = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                // Viết SQL gọn gàng, có dấu cách ở đầu mỗi dòng để không bị dính chữ
                String sql = "SELECT s.StudentID, s.Name, s.UserEmail, u.Password, u.PhoneNumber, u.Status, u.Role "
                        + " FROM [Student] s "
                        + " JOIN [User] u ON s.UserEmail = u.Email";

                st = cn.createStatement();
                table = st.executeQuery(sql);

                while (table.next()) {

                    String email = table.getString("UserEmail");
                    String password = table.getString("Password");
                    String role = table.getString("Role");
                    String phoneNumber = table.getString("PhoneNumber");
                    String status = table.getString("Status");
                    String studentID = table.getString("StudentID");
                    String name = table.getString("Name");

                    Student s = new Student(email, password, phoneNumber, role, status, studentID, name);
                    list.add(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Teacher> getAllTeacher() {
        ArrayList<Teacher> list = new ArrayList<>();
        Connection cn = null;
        Statement st = null;
        ResultSet table = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                // 1. Viết câu SQL JOIN để lấy full thông tin Teacher
                String sql = "SELECT U.Email, U.Password, U.Role, U.PhoneNumber, U.Status, T.TeacherID, T.Name "
                        + "FROM [User] U "
                        + "INNER JOIN Teacher T ON U.Email = T.UserEmail";

                st = cn.createStatement();
                table = st.executeQuery(sql);

                while (table.next()) {
                    // 2. Trích xuất dữ liệu từ các cột
                    String email = table.getString("Email");
                    String password = table.getString("Password");
                    String role = table.getString("Role");
                    String phoneNumber = table.getString("PhoneNumber");
                    String status = table.getString("Status");
                    String teacherID = table.getString("TeacherID");
                    String name = table.getString("Name");

                    // 3. Khởi tạo đối tượng Teacher 
                    // Hãy đảm bảo Class Teacher có Constructor khớp với thứ tự này
                    Teacher t = new Teacher(email, password, phoneNumber, role, status, teacherID, name);

                    list.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean banUser(String Email) {
        Connection cn = null;
        //ban người dùng thì chuyển rạng thái(status) từ active về banned
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                //viết sql
                String sql = "UPDATE [EducationDB].[dbo].[User]\n"
                        + "SET [Status] = 'Banned'\n"
                        + "WHERE [Email] = ?";
                //Chuẩn bị để gửi
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, Email);
                int result = st.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean UnbanUser(String Email) {
        Connection cn = null;

        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                //viết sql
                String sql = "UPDATE [EducationDB].[dbo].[User]\n"
                        + "SET [Status] = 'Active'\n"
                        + "WHERE [Email] = ?";
                //Chuẩn bị để gửi
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, Email);
                int result = st.executeUpdate();
                return result > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Teacher> SearchTeacherByName(String name) {
        Connection cn = null;
        ArrayList<Teacher> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT \n"
                        + "    t.TeacherID, \n"
                        + "    t.Name, \n"
                        + "    t.UserEmail, \n"
                        + "    u.Password, \n"
                        + "    u.Role, \n"
                        + "    u.PhoneNumber, \n"
                        + "    u.Status\n"
                        + "FROM [dbo].[Teacher] t\n"
                        + "INNER JOIN [dbo].[User] u ON t.UserEmail = u.Email\n"
                        + "WHERE t.Name LIKE ? OR t.UserEmail LIKE ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + name + "%");
                st.setString(2, "%" + name + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String TeacherID = table.getString("TeacherID");
                        String Name = table.getString("Name");
                        String UserEmail = table.getString("UserEmail");
                        String Password = table.getString("Password");
                        String PhoneNumber = table.getString("PhoneNumber");
                        String Status = table.getString("Status");
                        String Role = table.getString("Role");
                        list.add(new Teacher(UserEmail, Password, PhoneNumber, Role, Status, TeacherID, Name));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int sumStudent() {
        int total = 0;
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT COUNT(*) AS TotalStudents \n"
                        + "FROM [dbo].[User] \n"
                        + "WHERE Role = 'Student' AND Status = 'Active'";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                //Di chuyển con trỏ vào dòng dữ liệu đầu tiên
                if (table.next()) {
                    total = table.getInt("TotalStudents");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public ArrayList<Student> SearchStudentByName(String name) {
        Connection cn = null;
        ArrayList<Student> list = new ArrayList<>();
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "SELECT \n"
                        + "    s.StudentID, \n"
                        + "    s.Name, \n"
                        + "    s.UserEmail, \n"
                        + "    u.Password, \n"
                        + "    u.Role, \n"
                        + "    u.PhoneNumber, \n"
                        + "    u.Status\n"
                        + "FROM [dbo].[Student] s\n"
                        + "INNER JOIN [dbo].[User] u ON s.UserEmail = u.Email\n"
                        + "WHERE s.Name LIKE ? OR s.UserEmail LIKE ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%" + name + "%");
                st.setString(2, "%" + name + "%");
                ResultSet table = st.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        String StudentID = table.getString("StudentID");
                        String Name = table.getString("Name");
                        String UserEmail = table.getString("UserEmail");
                        String Password = table.getString("Password");
                        String PhoneNumber = table.getString("PhoneNumber");
                        String Status = table.getString("Status");
                        String Role = table.getString("Role");
                        list.add(new Student(UserEmail, Password, PhoneNumber, Role, Status, StudentID, Name));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
