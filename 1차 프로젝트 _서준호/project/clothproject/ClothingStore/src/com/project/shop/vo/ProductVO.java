package com.project.shop.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductVO {
    private int productNo;
    private String category;
    private String productName;
    private String country;
    private String material;
    private int price;
    private int stock;
}
