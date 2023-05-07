package com.project.shop.view;

import java.util.List;

import com.project.shop.controller.InvoiceController;
import com.project.shop.controller.MainController;
import com.project.shop.vo.InvoiceVO;

public class InvoiceView {

    public void invoiceListView(List<InvoiceVO> list) {
        System.out.println("========================================================");
        System.out.println("주문 목록");
        System.out.println("========================================================");
        System.out.println("주문번호\t|주문일시\t|상품명\t|주문 금액\t|수량");
        for (InvoiceVO invoice : list) {
            System.out.println(invoice.getInvoiceNo() + "\t|" + invoice.getInvoiceDate() + "\t|"
                + invoice.getProductName() + "\t|" + invoice.getPayment()
                + "\t|" + invoice.getQuantity());
        }
        System.out.println("========================================================");
        System.out.println("1. 주문취소하기 2. 메인화면으로 3. 종료");
        System.out.println("========================================================");
        System.out.println("번호를 입력해주세요.");

        int actionKey = MainController.instance.sc.nextInt();
        switch (actionKey) {
            case 1:
                System.out.println("========================================================");
                System.out.println("주문을 취소합니다. 취소할 주문번호를 입력해주세요.");
                int invoiceNo = MainController.instance.sc.nextInt();

                try {

                    InvoiceController.instance.deleteInvoiceByInvoiceNo(invoiceNo);
                } catch (Exception e1) {
                    
                    e1.printStackTrace();
                    System.out.println(e1.getMessage());
                }
                InvoiceController.instance.invoiceListView();
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

}
