package com.project.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.project.shop.exception.DataNotFoundException;
import com.project.shop.utils.DBConnectionUtil;
import com.project.shop.vo.GradeVO;

public class GradeDAO {
    
    public void insertGrade(GradeVO gradeVO) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "INSERT INTO GRADE "
            + " (GRADE_NO, GRADE_NM, GRADE_DC) "
            + " VALUES (?, ?, ?) ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, gradeVO.getGradeNo());
        pstmt.setString(2, gradeVO.getGradeNm());
        pstmt.setInt(3, gradeVO.getGradeDC());

        DBConnectionUtil.save(pstmt);
        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

    
    public void deleteGradeByGradeNo(int gradeNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "DELETE FROM GRADE WHERE GRADE_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, gradeNo);
        DBConnectionUtil.save(pstmt);

        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

    
    public GradeVO getGradeByGradeNo(int gradeNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM GRADE "
            + " WHERE GRADE_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, gradeNo);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        if (!resultSet.next()) {
            throw new DataNotFoundException("해당 권한이 없습니다.");
        }
        GradeVO grade = new GradeVO();
        grade.setGradeNo(resultSet.getInt("GRADE_NO"));
        grade.setGradeNm(resultSet.getString("GRADE_NM"));
        grade.setGradeDC(resultSet.getInt("GRADE_DC"));

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return grade;
    }

 
    public void modifyGradeDCByGradeNo(int gradeNo, int gradeDC) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "UPDATE GRADE SET GRADE_DC = ? WHERE GRADE_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, gradeDC);
        pstmt.setInt(2, gradeNo);
        DBConnectionUtil.save(pstmt);

        DBConnectionUtil.disConnection(null, pstmt, connection);
    }
}
