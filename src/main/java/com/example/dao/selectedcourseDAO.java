package com.example.dao;
import com.example.entity.selectedcourse;
import java.util.List;

public interface selectedcourseDAO {
    public int addselectedcourse(int courseID, int studentID);
    public int delete(int courseID,int studentID);
    public int delete(int courseID);
    public int setMark(int courseID,int studentID,int mark);
    public Integer getMark(int courseID,int studentID);
    public List<selectedcourse> getByCourseID(int courseID);
    public List<selectedcourse> getByStudentID(int studentID);

    // 添加选课记录
}
