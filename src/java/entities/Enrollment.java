/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author ACER
 */
public class Enrollment {
    private int enrollmentID;
    private String studentID; // FK
    private int courseID;     // FK
    private String status;
    private String registerDate;
    private String cancelDate;
    private String updateAt;

    // 2. CÁC THUỘC TÍNH BỔ SUNG (QUAN TRỌNG)
    // Dùng để hứng dữ liệu khi JOIN bảng
    // Thay vì chỉ lưu ID, ta lưu nguyên cả object để lấy tên, giá tiền, email...
    private Student student; 
    private Course course;

    public Enrollment() {
    }

    public Enrollment(int enrollmentID, String studentID, int courseID, String status, 
                      String registerDate, Student student, Course course) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.status = status;
        this.registerDate = registerDate;
        this.student = student;
        this.course = course;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getStatus() {
        return status;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}
