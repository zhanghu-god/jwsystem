package com.example.dao.daoimpl;

import java.sql.ResultSet;
import java.util.*;
import com.example.entity.selectedcourse;
import com.example.util.DBUtil;
import com.example.dao.selectedcourseDAO;
import java.sql.SQLException;
public class selectedcourseDAOimpl implements selectedcourseDAO{

    public void addselectedcourse(int courseID,int studentID){
        String sql = "insert into selectedcourse values(?,?,?)";
        Object[] object = {courseID,studentID,0};
        DBUtil.update(sql,object);
    }
    @Override
    public void delete(int courseID,int studentID){
        String sql = "delete from selectedcourse where courseID = ? and studentID = ?";
        Object[] object = {courseID,studentID};
        DBUtil.update(sql,object);
    }
    @Override
    public void  setMark(int courseID,int studentID,int mark){
        String sql = "update selectedcourse set mark = ? where courseID = ? and studentID = ?";
        Object[] object = {mark,courseID,studentID};
        DBUtil.update(sql,object);
    }
    @Override
    public List<selectedcourse>  getByCourseID(int courseID){
        String sql = "select * from selectedcourse where courseID = ?";
        Object[] object = {courseID};
        List<selectedcourse> list = new ArrayList<>();
        try{
        ResultSet resultSet = DBUtil.executeQuery(sql,object);
        while (resultSet.next()){
            selectedcourse selectedcourse = new selectedcourse();
            selectedcourse.setCourseID(resultSet.getInt("courseID"));
            selectedcourse.setStudentID(resultSet.getInt("studentID"));
            selectedcourse.setMark(resultSet.getInt("mark"));
            list.add(selectedcourse);
        }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }
    @Override
    public List<selectedcourse> getByStudentID(int studentID){
        String sql = "select * from selectedcourse where studentID = ?";
        Object[] object = {studentID};
        List<selectedcourse> list = new ArrayList<>();
        try{
        ResultSet resultSet = DBUtil.executeQuery(sql,object);
        while (resultSet.next()) {
            selectedcourse selectedcourse = new selectedcourse();
            selectedcourse.setCourseID(resultSet.getInt("courseID"));
            selectedcourse.setStudentID(resultSet.getInt("studentID"));
            selectedcourse.setMark(resultSet.getInt("mark"));
            list.add(selectedcourse);
        }
    }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return list;
    }
}
