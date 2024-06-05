package com.example.dao.daoimpl;

import java.sql.ResultSet;
import java.util.*;
import com.example.entity.selectedcourse;
import com.example.util.DBUtil;
import com.example.dao.selectedcourseDAO;
import java.sql.SQLException;
public class selectedcourseDAOimpl implements selectedcourseDAO{
    private static class ResultException extends Exception {
        public ResultException(String message) {
            super(message);
        }
    }
    public void addselectedcourse(int courseID,int studentID){
        String sql1 = "select * from selectedcourse where courseID = ? and studentID = ?";
        Object[] object1 = {courseID,studentID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1,object1);
        try{
            if (resultSet1.next()){
                throw new ResultException("已经选过该课程");
            }
            else{
                String sql = "insert into selectedcourse values(?,?,?)";
                Object[] object = {courseID,studentID,0};
                DBUtil.update(sql,object);
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void delete(int courseID,int studentID){
        String sql1 = "select * from selectedcourse where courseID = ? and studentID = ?";
        Object[] object1 = {courseID,studentID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1,object1);
        try{
            if (!resultSet1.next()){
                throw new ResultException("未选过该课程");
            }
            else{
                String sql = "delete from selectedcourse where courseID = ? and studentID = ?";
                Object[] object = {courseID,studentID};
                DBUtil.update(sql,object);
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void  setMark(int courseID,int studentID,int mark){
        String sql1  = "select * from selectedcourse where courseID = ? and studentID = ?";
        Object[] object1 = {courseID,studentID};
        ResultSet resultSet1 = DBUtil.executeQuery(sql1,object1);
        try{
            if (!resultSet1.next()){
                throw new ResultException("该学生未选过该课程");
        }
            else {
                String sql = "update selectedcourse set mark = ? where courseID = ? and studentID = ?";
                Object[] object = {mark, courseID, studentID};
                DBUtil.update(sql, object);
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<selectedcourse>  getByCourseID(int courseID){
        String sql = "select * from selectedcourse where courseID = ?";
        Object[] object = {courseID};
        List<selectedcourse> list = new ArrayList<>();
        try{
        ResultSet resultSet = DBUtil.executeQuery(sql,object);
        if (!resultSet.next()){
            throw new ResultException("该课程未选过学生");
        }
        else {
            do{
            selectedcourse selectedcourse = new selectedcourse();
            selectedcourse.setCourseID(resultSet.getInt("courseID"));
            selectedcourse.setStudentID(resultSet.getInt("studentID"));
            selectedcourse.setMark(resultSet.getInt("mark"));
            list.add(selectedcourse);
            }while (resultSet.next());
            return list;
        }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<selectedcourse> getByStudentID(int studentID){
        String sql = "select * from selectedcourse where studentID = ?";
        Object[] object = {studentID};
        List<selectedcourse> list = new ArrayList<>();
        try{
        ResultSet resultSet = DBUtil.executeQuery(sql,object);
        if(!resultSet.next()){
            throw new ResultException("该学生未选过课程");
        }
        else{
            do {
            selectedcourse selectedcourse = new selectedcourse();
            selectedcourse.setCourseID(resultSet.getInt("courseID"));
            selectedcourse.setStudentID(resultSet.getInt("studentID"));
            selectedcourse.setMark(resultSet.getInt("mark"));
            list.add(selectedcourse);
            }while (resultSet.next());
        return list;
        }
    }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
