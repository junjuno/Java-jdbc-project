package com.project.shop.service;

import java.util.List;
import java.util.Objects;

import com.project.shop.dao.InvoiceDAO;
import com.project.shop.exception.DataNotFoundException;
import com.project.shop.vo.InvoiceVO;

public class InvoiceService {
    private final InvoiceDAO dao = new InvoiceDAO();

  
    public void insertInvoice(InvoiceVO invoiceVO) throws Exception {
        dao.insertInvoice(invoiceVO);
    }

    
    public void deleteInvoiceByInvoiceNo(int invoiceNo) throws Exception {

        if (Objects.isNull(findInvoiceByInvoiceNo(invoiceNo))) {
            throw new DataNotFoundException("존재하지 않는 주문번호 입니다.");
        }
        dao.deleteInvoiceByInvoiceNo(invoiceNo);
    }

   
    public InvoiceVO findInvoiceByInvoiceNo(int invoiceNo) throws Exception {
        return dao.findInvoiceByInvoiceNo(invoiceNo);
    }

   
    public List<InvoiceVO> findAllInvoicesByMemberNo(int memberNo) throws Exception {
        return dao.findAllInvoicesByMemberNo(memberNo);
    }

    
    public List<InvoiceVO> findAllInvoices() throws Exception {
        return dao.findAllInvoices();
    }
}
