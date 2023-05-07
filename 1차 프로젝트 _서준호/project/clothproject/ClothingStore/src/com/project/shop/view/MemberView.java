package com.project.shop.view;

import java.util.HashMap;
import java.util.Map;

import com.project.shop.controller.MainController;
import com.project.shop.controller.MemberController;
import com.project.shop.utils.UniqueIdUtil;
import com.project.shop.vo.MemberVO;

public class MemberView {

    public void joinMemberView() {
        System.out.println("========================================================");
        System.out.println("회원가입");
        System.out.println("========================================================");
        int memberNo = UniqueIdUtil.generateIdByTime();
        System.out.println("회원아이디 : ");
        String userId = MainController.instance.sc.next();
        System.out.println("회원이름 : ");
        String name = MainController.instance.sc.next();
        System.out.println("비밀번호 : ");
        String password = MainController.instance.sc.next();
        System.out.println("회원전화번호 : ");
        String phone = MainController.instance.sc.next();
        System.out.println("회원주소 : ");
        String address = MainController.instance.sc.next();
        System.out.println("회원권한코드 : ");
        String gradeNo = MainController.instance.sc.next();

        MemberVO memberVO = new MemberVO();
        memberVO.setMemberPw(password);
        memberVO.setAddress(address);
        memberVO.setGradeNo(Integer.valueOf(gradeNo));
        memberVO.setMemberId(userId);
        memberVO.setName(name);
        memberVO.setPhone(phone);
        memberVO.setMemberNo(memberNo);
        memberVO.setMileage(0);
        try {
            MemberController.instance.joinMember(memberVO);
            System.out.println("가입되었습니다.");
        } catch (Exception e1) {
            
            e1.printStackTrace();
            System.out.println(e1.getMessage());
        }

        MainController.instance.start();
    }

    public void modifyMemberView(MemberVO memberVO) {
        System.out.println("========================================================");
        System.out.println("마이페이지");
        System.out.println("회원번호 : " + memberVO.getMemberNo());
        System.out.println("회원아이디 : " + memberVO.getMemberId());
        System.out.println("회원이름 : " + memberVO.getName());
        System.out.println("회원전화번호 : " + memberVO.getPhone());
        System.out.println("회원주소 : " + memberVO.getAddress());
        System.out.println("회원권한코드 : " + memberVO.getGradeNo());

        System.out.println("========================================================");
        System.out.println("1. 수정하기 2. 탈퇴 3. 메인화면으로 4. 종료");
        System.out.println("========================================================");
        System.out.println("번호를 입력해주세요.");

        int actionKey = MainController.instance.sc.nextInt();
        switch (actionKey) {
            case 1:
                System.out.println("========================================================");
                System.out.println("회원 정보를 수정합니다.");
                System.out.println("회원아이디 : ");
                String userId = MainController.instance.sc.next();
                System.out.println("회원이름 : ");
                String name = MainController.instance.sc.next();
                System.out.println("비밀번호 : ");
                String password = MainController.instance.sc.next();
                System.out.println("회원전화번호 : ");
                String phone = MainController.instance.sc.next();
                System.out.println("회원주소 : ");
                String address = MainController.instance.sc.next();
                System.out.println("회원권한코드 : ");
                int gradeNo = MainController.instance.sc.nextInt();

                memberVO.setMemberPw(password);
                memberVO.setAddress(address);
                memberVO.setGradeNo(gradeNo);
                memberVO.setMemberId(userId);
                memberVO.setName(name);
                memberVO.setPhone(phone);
                try {
                    MemberController.instance.modifyMember(memberVO);
                } catch (Exception e1) {
                    
                    e1.printStackTrace();
                    System.out.println(e1.getMessage());
                }

                modifyMemberView(memberVO);
                break;
            case 2:
                System.out.println("========================================================");
                System.out.println("이 작업은 되돌릴수 없습니다. 정말 탈퇴하시겠습니까? (Y/N)");
                String yn = MainController.instance.sc.next();
                if ("N".equals(yn)) {

                    modifyMemberView(memberVO);
                } else {

                    try {
                        MemberController.instance.deleteMemberByMemberNo(memberVO.getMemberNo());
                        System.out.println("탈퇴되었습니다.");
                        MainController.instance.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(e.getMessage());
                        modifyMemberView(memberVO);
                    }
                }
                break;
            case 3:

                MainController.instance.start();
                break;
            case 4:

                MainController.instance.end();
                break;
            default:
                break;
        }
    }

    public void signInMemberView() {
        System.out.println("========================================================");
        System.out.println("로그인 아이디를 입력해주세요.");
        String userId = MainController.instance.sc.next();
        System.out.println("로그인 비밀번호를 입력해주세요.");
        String userPw = MainController.instance.sc.next();

        try {
            Map param = new HashMap<String, String>();
            param.put("memberId", userId);
            param.put("memberPassword", userPw);
            MemberVO member = MemberController.instance.signInMember(param);
            MemberController.instance.memberSession = member;
            System.out.println("환영합니다. " + member.getName() + "님");
        } catch (Exception e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
            signInMemberView();
        }
    }

}
