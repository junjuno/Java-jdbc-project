package com.project.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.shop.exception.DataNotFoundException;
import com.project.shop.utils.DBConnectionUtil;
import com.project.shop.vo.InvoiceVO;

public class InvoiceDAO {
    
    public void insertInvoice(InvoiceVO invoiceVO) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "INSERT INTO INVOICE "
            + " (INVOICE_NO, QUANTITY, MEMBER_NO, INVOICE_DATE, PAYMENT, PRODUCT_NO) "
            + " VALUES (?, ?, ?, ?, ?, ?) ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, invoiceVO.getInvoiceNo());
        pstmt.setInt(2, invoiceVO.getQuantity());
        pstmt.setInt(3, invoiceVO.getMemberNo());
        pstmt.setString(4, invoiceVO.getInvoiceDate());
        pstmt.setInt(5, invoiceVO.getPayment());
        pstmt.setInt(6, invoiceVO.getProductNo());

        DBConnectionUtil.save(pstmt);
        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

   
    public void deleteInvoiceByInvoiceNo(int invoiceNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "DELETE FROM INVOICE WHERE INVOICE_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, invoiceNo);
        DBConnectionUtil.save(pstmt);

        DBConnectionUtil.disConnection(null, pstmt, connection);
    }

  
    public InvoiceVO findInvoiceByInvoiceNo(int invoiceNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT "
            + " INVOICE_NO, QUANTITY, MEMBER_NO, TO_CHAR(INVOICE_DATE, 'YYYY-MM-DD') as INVOICE_DATE, PAYMENT, PRODUCT_NO FROM INVOICE "
            + " WHERE INVOICE_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, invoiceNo);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        if (!resultSet.next()) {
            throw new DataNotFoundException("해당 상품을 찾을 수 없습니다.");
        }
        InvoiceVO invoice = new InvoiceVO();
        invoice.setInvoiceNo(resultSet.getInt("INVOICE_NO"));
        invoice.setQuantity(resultSet.getInt("QUANTITY"));
        invoice.setMemberNo(resultSet.getInt("MEMBER_NO"));
        invoice.setInvoiceDate(resultSet.getString("INVOICE_DATE"));
        invoice.setPayment(resultSet.getInt("PAYMENT"));
        invoice.setProductNo(resultSet.getInt("PRODUCT_NO"));

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return invoice;
    }

   
    public List<InvoiceVO> findAllInvoicesByMemberNo(int memberNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT INVOICE_NO, QUANTITY, MEMBER_NO, TO_CHAR(INVOICE_DATE, 'YYYY-MM-DD') as INVOICE_DATE, PAYMENT, PRODUCT_NO FROM INVOICE"
            + " WHERE MEMBER_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, memberNo);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        List<InvoiceVO> invoices = new ArrayList<InvoiceVO>();
        while (resultSet.next()) {
            InvoiceVO invoice = new InvoiceVO();
            invoice.setInvoiceNo(resultSet.getInt("INVOICE_NO"));
            invoice.setQuantity(resultSet.getInt("QUANTITY"));
            invoice.setMemberNo(resultSet.getInt("MEMBER_NO"));
            invoice.setInvoiceDate(resultSet.getString("INVOICE_DATE"));
            invoice.setPayment(resultSet.getInt("PAYMENT"));
            invoice.setProductNo(resultSet.getInt("PRODUCT_NO"));
            invoices.add(invoice);
        }

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return invoices;
    }

    
    public List<InvoiceVO> findAllInvoices() throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT "
            + " i.INVOICE_NO, i.QUANTITY, i.MEMBER_NO, TO_CHAR(i.INVOICE_DATE, 'YYYY-MM-DD') as INVOICE_DATE, i.PAYMENT, i.PRODUCT_NO, p.PRODUCT_NAME "
            + " FROM INVOICE i JOIN PRODUCT p ON i.PRODUCT_NO = p.PRODUCT_NO ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        List<InvoiceVO> invoices = new ArrayList<InvoiceVO>();
        while (resultSet.next()) {
            InvoiceVO invoice = new InvoiceVO();
            invoice.setInvoiceNo(resultSet.getInt("INVOICE_NO"));
            invoice.setQuantity(resultSet.getInt("QUANTITY"));
            invoice.setMemberNo(resultSet.getInt("MEMBER_NO"));
            invoice.setInvoiceDate(resultSet.getString("INVOICE_DATE"));
            invoice.setPayment(resultSet.getInt("PAYMENT"));
            invoice.setProductNo(resultSet.getInt("PRODUCT_NO"));
            invoice.setProductName(resultSet.getString("PRODUCT_NAME"));
            invoices.add(invoice);
        }

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return invoices;
    }
}
