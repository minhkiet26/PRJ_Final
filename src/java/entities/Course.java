/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Admin
 */
public class Course {
    private String CourseID;
    private String CourseName;
    private String Description;
    private String TuitionFee;
    private String TeacherID;
    private String ImageURL;
    private String StudyTime;
    private String Schedule;
    private String StartDate;
    private String TotalLectures;
    private String NumberEnrolled;


    public Course() {
    }

    public Course(String CourseID, String CourseName, String Description, String TuitionFee, String TeacherID, String ImageURL, String StudyTime, String Schedule, String StartDate, String TotalLectures, String NumberEnrolled) {
        this.CourseID = CourseID;
        this.CourseName = CourseName;
        this.Description = Description;
        this.TuitionFee = TuitionFee;
        this.TeacherID = TeacherID;
        this.ImageURL = ImageURL;
        this.StudyTime = StudyTime;
        this.Schedule = Schedule;
        this.StartDate = StartDate;
        this.TotalLectures = TotalLectures;
        this.NumberEnrolled = NumberEnrolled;
    }
    

    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String CourseID) {
        this.CourseID = CourseID;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getTuitionFee() {
        return TuitionFee;
    }

    public void setTuitionFee(String TuitionFee) {
        this.TuitionFee = TuitionFee;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public String getNumberEnrolled() {
        return NumberEnrolled;
    }

    public void setTeacherID(String TeacherID) {
        this.TeacherID = TeacherID;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String ImageURL) {
        this.ImageURL = ImageURL;
    }

    public String getStudyTime() {
        return StudyTime;
    }

    public void setStudyTime(String StudyTime) {
        this.StudyTime = StudyTime;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String Schedule) {
        this.Schedule = Schedule;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getTotalLectures() {
        return TotalLectures;
    }

    public void setTotalLectures(String TotalLectures) {
        this.TotalLectures = TotalLectures;
    }

    public void setNumberEnrolled(String NumberEnrolled) {
        this.NumberEnrolled = NumberEnrolled;
    }

}
