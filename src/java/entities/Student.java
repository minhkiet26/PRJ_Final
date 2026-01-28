/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Admin
 */
public class Student extends User{
    private String StudentID;
    private String Name;

    public Student() {
    }

    public Student(String StudentID, String Name) {
        this.StudentID = StudentID;
        this.Name = Name;
    }

    public Student(String Email, String Password, String PhoneNumber, String Role,String StudentID, String Name) {
        super( Email, Password, PhoneNumber, Role);
        this.StudentID = StudentID;
        this.Name = Name;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
}
