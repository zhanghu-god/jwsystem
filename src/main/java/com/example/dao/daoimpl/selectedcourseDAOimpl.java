package com.example.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int addselectedcourse(int courseID,int studentID){
        String sql1 = "select * from selectedcourse where courseID = ? and studentID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            stmt.setObject(2, studentID);
            rs1 = stmt.executeQuery();
            if (rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("已经选过该课程");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "insert into selectedcourse values(?,?,?)";
                Object[] object = {courseID,studentID,null};
                DBUtil.update(sql,object);
                conduct = 1;
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }
    @Override
    public int delete(int courseID,int studentID){
        String sql1 = "select * from selectedcourse where courseID = ? and studentID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            stmt.setObject(2, studentID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("未选过该课程");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "delete from selectedcourse where courseID = ? and studentID = ?";
                Object[] object = {courseID,studentID};
                DBUtil.update(sql,object);
                conduct = 1;
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }

    @Override
    public int delete(int courseID){
        String sql1 = "select * from selectedcourse where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("未选过该课程");
            }
            else{
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "delete from selectedcourse where courseID = ? ";
                Object[] object = {courseID};
                DBUtil.update(sql,object);
                conduct = 1;
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }
    @Override
    public int  setMark(int courseID,int studentID,int mark){
        String sql1  = "select * from selectedcourse where courseID = ? and studentID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        int conduct = 0;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            stmt.setObject(2, studentID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("该学生未选过该课程");
        }
            else {
                DBUtil.closeAll(rs1, stmt, conn);
                String sql = "update selectedcourse set mark = ? where courseID = ? and studentID = ?";
                Object[] object = {mark, courseID, studentID};
                DBUtil.update(sql, object);
                conduct = 1;
            }
        }
        catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }finally {
            return conduct;
        }
    }
    @Override
    public Integer getMark(int courseID,int studentID){
        String sql1  = "select * from selectedcourse where courseID = ? and studentID = ? and mark is not null";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        Integer mark;
        try{
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql1);
            stmt.setObject(1, courseID);
            stmt.setObject(2, studentID);
            rs1 = stmt.executeQuery();
            if (!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
                throw new ResultException("没有成绩");
            }else{
                mark = rs1.getInt("mark");
                return mark;
            }
        }catch (ResultException | SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<selectedcourse>  getByCourseID(int courseID){
        String sql = "select * from selectedcourse where courseID = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, courseID);
            rs1 = stmt.executeQuery();
        if (!rs1.next()){
            DBUtil.closeAll(rs1, stmt, conn);
            throw new ResultException("该课程未选过学生");
        }
        else {
            List<selectedcourse> list = new ArrayList<>();
            do{
            selectedcourse selectedcourse = new selectedcourse();
            selectedcourse.setCourseID(rs1.getInt("courseID"));
            selectedcourse.setStudentID(rs1.getInt("studentID"));
            selectedcourse.setMark(rs1.getInt("mark"));
            list.add(selectedcourse);
            }while (rs1.next());
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
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs1 = null;
        try {
            conn = DBUtil.getconn();
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, studentID);
            rs1 = stmt.executeQuery();
            if(!rs1.next()){
                DBUtil.closeAll(rs1, stmt, conn);
            throw new ResultException("该学生未选过课程");
        }
        else{
            List<selectedcourse> list = new ArrayList<>();
            do {
            selectedcourse selectedcourse = new selectedcourse();
            selectedcourse.setCourseID(rs1.getInt("courseID"));
            selectedcourse.setStudentID(rs1.getInt("studentID"));
            selectedcourse.setMark(rs1.getInt("mark"));
            list.add(selectedcourse);
            }while (rs1.next());
            DBUtil.closeAll(rs1, stmt, conn);
            return list;
        }
    }
        catch (SQLException | ResultException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
