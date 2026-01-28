/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Admin
 */
public class Teacher extends User{
    private String TeacherID;
    private String Name;

    public Teacher() {
    }

    public Teacher(String TeacherID, String Name) {
        this.TeacherID = TeacherID;
        this.Name = Name;
    }

    public Teacher( String Email, String Password, String PhoneNumber, String Role, String TeacherID, String Name) {
        super( Email, Password, PhoneNumber, Role);
        this.TeacherID = TeacherID;
        this.Name = Name;
    }

    public String getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(String TeacherID) {
        this.TeacherID = TeacherID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
}
