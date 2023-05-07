package com.project.shop.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.project.shop.controller.GradeController;
import com.project.shop.controller.InvoiceController;
import com.project.shop.controller.MainController;
import com.project.shop.controller.MemberController;
import com.project.shop.controller.ProductController;
import com.project.shop.utils.UniqueIdUtil;
import com.project.shop.vo.GradeVO;
import com.project.shop.vo.InvoiceVO;
import com.project.shop.vo.MemberVO;
import com.project.shop.vo.ProductVO;

public class ProductView {

    public void productListView(List<ProductVO> list) {
        System.out.println("========================================================");
        System.out.println("상품 목록");
        System.out.println("========================================================");
        System.out.println("상품번호\t|카테고리\t|상품명\t|가격\t|재고수량");
        for (ProductVO product : list) {
            System.out.println(product.getProductNo() + "\t|" + product.getCategory() + "\t|" + product.getProductName()
                + "\t|" + product.getPrice() + "\t|" + product.getStock());
        }
        System.out.println("========================================================");
        System.out.println("1. 상품상세보기 2. 메인화면으로 3. 종료");
        System.out.println("========================================================");
        System.out.println("번호를 입력해주세요.");

        int actionKey = MainController.instance.sc.nextInt();
        switch (actionKey) {
            case 1:
                System.out.println("========================================================");
                System.out.println("상품 상세 화면으로 이동합니다. 선택할 상품번호를 입력해주세요.");
                int productNo = MainController.instance.sc.nextInt();

                try {

                    ProductController.instance.productDetailView(productNo);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    System.out.println(e1.getMessage());
                    productListView(list);
                }
                break;
            case 2:

                MainController.instance.start();
                break;
            case 3:

                MainController.instance.end();
                break;
            default:
                break;
        }
    }

    public void productDetailView(ProductVO detail) {
        System.out.println("========================================================");
        System.out.println("상품 상세 화면");
        System.out.println("========================================================");
        System.out.println("상품번호\n|카테고리\t|상품명\t|가격\t|재고수량");
        System.out.println("상품번호 : " + detail.getProductNo());
        System.out.println("카테고리 : " + detail.getCategory());
        System.out.println("상품명 : " + detail.getProductName());
        System.out.println("가격" + detail.getPrice());
        System.out.println("재고수량 : " + detail.getStock());
        System.out.println("원산지 : " + detail.getCountry());
        System.out.println("재질 : " + detail.getMaterial());

        System.out.println("========================================================");
        System.out.println("1. 주문하기 2. 상품목록 화면으로 3. 메인화면으로 4. 종료");
        System.out.println("========================================================");
        System.out.println("번호를 입력해주세요.");

        int actionKey = MainController.instance.sc.nextInt();
        switch (actionKey) {
            case 1:
                System.out.println("========================================================");
                System.out.println("상품을 주문합니다. 주문하실 수량을 입력해주세요.");
                int quantity = MainController.instance.sc.nextInt();

                try {

                    MemberVO member = MemberController.memberSession;
                    if (member == null) {
                        System.out.println("로그인을 먼저 하여주세요.");
                        MemberController.instance.signInMemberView();
                        return;
                    }
                    if (quantity > detail.getStock()) {
                        System.out.println("구매하시려는 수량은 재고수량보다 클 수 없습니다.");
                        productDetailView(detail);
                        return;
                    }
                    GradeVO grade = GradeController.instance.getGradeByGradeNo(member.getGradeNo());
                   
                    if(grade == null) {
                    	
                    	grade = new GradeVO();
                    	grade.setGradeDC(0);
                    	grade.setGradeNo(3);
                    	grade.setGradeNm("B grade");
                    }

                    InvoiceVO invoiceVO = new InvoiceVO();
                    int invoiceNo = UniqueIdUtil.generateIdByTime();
                    invoiceVO.setInvoiceNo(invoiceNo);
                    invoiceVO.setMemberNo(member.getMemberNo());
                    invoiceVO.setProductNo(detail.getProductNo());
                    invoiceVO.setPayment((quantity * detail.getPrice()) * (100 - grade.getGradeDC()) / 100);
                    // (quantity * detail.getPrice()) * (100 - grade.getGradeDC()) / 100
                    // (상품 수량 * 단품 가격) * (100% - 등급할인율) / 100% = 할인이 적용된 금액
                    invoiceVO.setQuantity(quantity);
                    invoiceVO.setInvoiceDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    InvoiceController.instance.insertInvoice(invoiceVO);
                    System.out.println("주문에 성공하였습니다.");
                    InvoiceController.instance.invoiceListView();
                } catch (Exception e1) {
                    
                    e1.printStackTrace();
                    System.out.println(e1.getMessage());
                    productDetailView(detail);
                }
                break;
            case 2:

                ProductController.instance.productListView();
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
}
