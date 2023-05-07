package com.project.shop.controller;

import java.util.List;
import java.util.Map;

import com.project.shop.service.MemberService;
import com.project.shop.view.MemberView;
import com.project.shop.vo.MemberVO;

public class MemberController {

    private MemberView view = new MemberView();
    private MemberService service = new MemberService();
    public static MemberController instance = new MemberController();

    public static MemberVO memberSession;

   
    public void joinMemberView() {
        view.joinMemberView();
    }


    public void joinMember(MemberVO memberVO) throws Exception {
        if (memberVO.getGradeNo() < 1) {
            memberVO.setGradeNo(3); 
        }
        service.joinMember(memberVO);
    }

  
    public void modifyMemberView() {
        try {
            view.modifyMemberView(findMemberByMemberId(memberSession.getMemberId()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류 발생 : 500");
            MainController.instance.start();
        }
    }

  
    public void modifyMember(MemberVO memberVO) throws Exception {
        service.modifyMember(memberVO);
    }


    public void deleteMemberByMemberNo(int memberNo) throws Exception {
        service.deleteMemberByMemberNo(memberNo);
    }

    
    public void signInMemberView() {
        view.signInMemberView();
    }


    public MemberVO signInMember(Map<String, String> map) throws Exception {
        return service.signInMember(map);
    }

 
    public MemberVO findMemberByMemberId(String memberId) throws Exception {
        return service.findMemberByMemberId(memberId);
    }

  
    public MemberVO findMemberByMemberNo(int memberNo) throws Exception {
        return service.findMemberByMemberNo(memberNo);
    }


    public List<MemberVO> findAllMembers() throws Exception {
        return service.findAllMembers();
    }
}
