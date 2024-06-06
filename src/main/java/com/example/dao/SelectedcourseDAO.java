package com.example.dao;
import com.example.entity.selectedcourse;
import java.util.List;

public interface selectedcourseDAO {
    public void addselectedcourse(int courseID, int studentID);
    public void delete(int courseID,int studentID);
    public void setMark(int courseID,int studentID,int mark);
    public List<selectedcourse> getByCourseID(int courseID);
    public List<selectedcourse> getByStudentID(int studentID);

    // 添加选课记录
}
