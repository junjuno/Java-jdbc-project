package com.project.shop.view;

import com.project.shop.controller.MainController;
import com.project.shop.controller.MemberController;
import com.project.shop.controller.ProductController;

public class MainView {

    public void main() {

        System.out.println("========================================================");
        System.out.println("clothing store");
        System.out.println("========================================================");
        System.out.println("1. 회원가입 2. 로그인 3. 상품검색 4. 마이페이지 5. 종료");
        System.out.println("========================================================");
        System.out.println("번호를 입력해주세요.");

        int actionKey = MainController.instance.sc.nextInt();
        switch (actionKey) {
            case 1:

                MemberController.instance.joinMemberView();
                break;
            case 2:

                MemberController.instance.signInMemberView();
                break;
            case 3:
            	ProductController.instance.productListView();
                break;
            case 4:

                if (MemberController.instance.memberSession == null) {
                    System.out.println("먼저 로그인하여주세요.");
                    main();
                }
                MemberController.instance.modifyMemberView();
                break;
            case 5:

                MainController.instance.end();
                break;
            default:
                break;
        }
        main();
    }
}
