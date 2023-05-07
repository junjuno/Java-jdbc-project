package com.project.shop.controller;

import java.util.List;

import com.project.shop.service.InvoiceService;
import com.project.shop.view.InvoiceView;
import com.project.shop.vo.InvoiceVO;

public class InvoiceController {

    private InvoiceView view = new InvoiceView();
    private InvoiceService service = new InvoiceService();
    public static InvoiceController instance = new InvoiceController();


    public void invoiceListView() {
        try {
            List<InvoiceVO> list = findAllInvoices();
            view.invoiceListView(list);
        } catch (Exception e) {
          
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        MainController.instance.start();
    }


    public void insertInvoice(InvoiceVO invoiceVO) throws Exception {
        service.insertInvoice(invoiceVO);
    }


    public void deleteInvoiceByInvoiceNo(int invoiceNo) throws Exception {
        service.deleteInvoiceByInvoiceNo(invoiceNo);
    }

  
    public InvoiceVO findInvoiceByInvoiceNo(int invoiceNo) throws Exception {
        return service.findInvoiceByInvoiceNo(invoiceNo);
    }


    public List<InvoiceVO> findAllInvoicesByMemberNo(int memberNo) throws Exception {
        return service.findAllInvoicesByMemberNo(memberNo);
    }


    public List<InvoiceVO> findAllInvoices() throws Exception {
        return service.findAllInvoices();
    }
}
