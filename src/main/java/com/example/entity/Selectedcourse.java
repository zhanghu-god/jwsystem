package com.example.entity;

public class selectedcourse {
    private int courseID;
    private int studentID;
    private int mark;

    public selectedcourse() {
    }

    public selectedcourse(int courseID, int studentID, int mark) {
        this.courseID = courseID;
        this.studentID = studentID;
        this.mark = mark;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
