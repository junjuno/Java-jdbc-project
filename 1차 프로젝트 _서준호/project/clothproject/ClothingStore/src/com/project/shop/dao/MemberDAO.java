package com.project.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.project.shop.exception.DataNotFoundException;
import com.project.shop.utils.DBConnectionUtil;
import com.project.shop.vo.MemberVO;

public class MemberDAO {
    
    public void joinMember(MemberVO memberVO) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "INSERT INTO MEMBER "
            + " (MEMBER_NO, MEMBER_ID, MEMBER_PW, NAME, ADDRESS, PHONE, GRADE_NO, MILEAGE) "
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, memberVO.getMemberNo());
        pstmt.setString(2, memberVO.getMemberId());
        pstmt.setString(3, memberVO.getMemberPw());
        pstmt.setString(4, memberVO.getName());
        pstmt.setString(5, memberVO.getAddress());
        pstmt.setString(6, memberVO.getPhone());
        pstmt.setInt(7, memberVO.getGradeNo());
        pstmt.setInt(8, memberVO.getMileage());

        DBConnectionUtil.save(pstmt);
        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

   
    public void modifyMember(MemberVO memberVO) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "UPDATE MEMBER "
            + " SET MEMBER_ID = ?, MEMBER_PW = ?, NAME = ?, ADDRESS = ?, PHONE = ?, GRADE_NO = ?, MILEAGE = ? "
            + " WHERE MEMBER_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, memberVO.getMemberId());
        pstmt.setString(2, memberVO.getMemberPw());
        pstmt.setString(3, memberVO.getName());
        pstmt.setString(4, memberVO.getAddress());
        pstmt.setString(5, memberVO.getPhone());
        pstmt.setInt(6, memberVO.getGradeNo());
        pstmt.setInt(7, memberVO.getMileage());
        pstmt.setInt(8, memberVO.getMemberNo());

        DBConnectionUtil.save(pstmt);
        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

   
    public void deleteMemberByMemberNo(int memberNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "DELETE FROM MEMBER WHERE MEMBER_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, memberNo);
        DBConnectionUtil.save(pstmt);

        

        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

   
    public MemberVO signInMember(Map<String, String> map) throws Exception {

        MemberVO member = findMemberByMemberId(map.get("memberId"));

        if (!member.getMemberPw().equals(map.get("memberPassword"))) {
            throw new DataNotFoundException("비밀번호가 맞지 않습니다.");
        }
        return member;
    }

    
    public MemberVO findMemberByMemberId(String memberId) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM MEMBER "
            + " WHERE MEMBER_ID = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, memberId);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        if (!resultSet.next()) {
            throw new DataNotFoundException("해당 아이디로 가입된 회원이 없습니다.");
        }
        String password = resultSet.getString("MEMBER_PW");
        MemberVO member = new MemberVO();
        member.setMemberNo(resultSet.getInt("MEMBER_NO"));
        member.setMemberId(resultSet.getString("MEMBER_ID"));
        member.setGradeNo(resultSet.getInt("GRADE_NO"));
        member.setMemberPw(password);
        member.setMileage(resultSet.getInt("MILEAGE"));
        member.setName(resultSet.getString("NAME"));
        member.setAddress(resultSet.getString("PHONE"));
        member.setPhone(resultSet.getString("ADDRESS"));

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return member;
    }

    public MemberVO findMemberByMemberNo(int memberNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM MEMBER "
            + " WHERE MEMBER_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, memberNo);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        if (!resultSet.next()) {
            throw new DataNotFoundException("해당 아이디로 가입된 회원이 없습니다.");
        }
        String password = resultSet.getString("MEMBER_PW");
        MemberVO member = new MemberVO();
        member.setMemberNo(resultSet.getInt("MEMBER_NO"));
        member.setMemberId(resultSet.getString("MEMBER_ID"));
        member.setGradeNo(resultSet.getInt("GRADE_NO"));
        member.setMemberPw(password);
        member.setMileage(resultSet.getInt("MILEAGE"));
        member.setName(resultSet.getString("NAME"));
        member.setAddress(resultSet.getString("PHONE"));
        member.setPhone(resultSet.getString("ADDRESS"));

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return member;
    }

 
    public List<MemberVO> findAllMembers() throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM MEMBER";

        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        List<MemberVO> members = new ArrayList<MemberVO>();
        while (resultSet.next()) {
            MemberVO member = new MemberVO();
            member.setMemberNo(resultSet.getInt("MEMBER_NO"));
            member.setMemberId(resultSet.getString("MEMBER_ID"));
            member.setGradeNo(resultSet.getInt("GRADE_NO"));
            member.setMemberPw(resultSet.getString("MEMBER_PW"));
            member.setMileage(resultSet.getInt("MILEAGE"));
            member.setName(resultSet.getString("NAME"));
            member.setAddress(resultSet.getString("PHONE"));
            member.setPhone(resultSet.getString("ADDRESS"));
            members.add(member);
        }

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return members;
    }
}
