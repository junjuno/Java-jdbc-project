package com.project.shop.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.project.shop.dao.MemberDAO;
import com.project.shop.exception.DataNotFoundException;
import com.project.shop.vo.MemberVO;

public class MemberService {
    private final MemberDAO dao = new MemberDAO();

   
    public void joinMember(MemberVO memberVO) throws Exception {

    	
        dao.joinMember(memberVO);
    }

    
    public void modifyMember(MemberVO memberVO) throws Exception {
        
        if (Objects.isNull(findMemberByMemberNo(memberVO.getMemberNo()))) {
            throw new DataNotFoundException("존재하지 않는 회원 입니다.");
        }
        dao.modifyMember(memberVO);
    }

  
    public void deleteMemberByMemberNo(int memberNo) throws Exception {
        
        if (Objects.isNull(findMemberByMemberNo(memberNo))) {
            throw new DataNotFoundException("존재하지 않는 회원 입니다.");
        }
        dao.deleteMemberByMemberNo(memberNo);
    }


    public MemberVO signInMember(Map<String, String> map) throws Exception {
        return dao.signInMember(map);
    }

   
    public MemberVO findMemberByMemberId(String memberId) throws Exception {
        return dao.findMemberByMemberId(memberId);
    }

   
    public MemberVO findMemberByMemberNo(int memberNo) throws Exception {
        return dao.findMemberByMemberNo(memberNo);
    }

   
    public List<MemberVO> findAllMembers() throws Exception {
        return dao.findAllMembers();
    }
}
