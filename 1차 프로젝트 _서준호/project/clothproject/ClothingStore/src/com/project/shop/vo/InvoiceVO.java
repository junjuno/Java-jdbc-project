package com.project.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceVO {
    private int invoiceNo;
    private int quantity;
    private int memberNo;
    private String invoiceDate;
    private int payment;
    private int productNo;
    
    private String productName;
}
