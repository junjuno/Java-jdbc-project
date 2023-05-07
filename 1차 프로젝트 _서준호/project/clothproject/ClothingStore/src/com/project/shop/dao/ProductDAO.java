package com.project.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.project.shop.exception.DataNotFoundException;
import com.project.shop.utils.DBConnectionUtil;
import com.project.shop.vo.ProductVO;

public class ProductDAO {

    public void insertProduct(ProductVO productVO) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "INSERT INTO PRODUCT "
            + " (PRODUCT_NO, CATEGORY, PRODUCT_NAME, COUNTRY, MATERIAL, PRICE, STOCK) "
            + " VALUES (?, ?, ?, ?, ?, ?, ?) ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, productVO.getProductNo());
        pstmt.setString(2, productVO.getCategory());
        pstmt.setString(3, productVO.getProductName());
        pstmt.setString(4, productVO.getCountry());
        pstmt.setString(5, productVO.getMaterial());
        pstmt.setInt(6, productVO.getPrice());
        pstmt.setInt(7, productVO.getStock());

        DBConnectionUtil.save(pstmt);
        DBConnectionUtil.disConnection(null, pstmt, connection);
    }


    public void modifyProduct(ProductVO productVO) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "UPDATE PRODUCT "
            + " SET CATEGORY = ?, PRODUCT_NAME = ?, COUNTRY = ?, MATERIAL = ?, PRICE = ?, STOCK = ? "
            + " WHERE PRODUCT_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, productVO.getCategory());
        pstmt.setString(2, productVO.getProductName());
        pstmt.setString(3, productVO.getCountry());
        pstmt.setString(4, productVO.getMaterial());
        pstmt.setInt(5, productVO.getPrice());
        pstmt.setInt(6, productVO.getStock());
        pstmt.setInt(7, productVO.getProductNo());

        DBConnectionUtil.save(pstmt);
        DBConnectionUtil.disConnection(null, pstmt, connection);
    }


    public void deleteProductByProductNo(int productNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "DELETE FROM PRODUCT WHERE PRODUCT_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, productNo);
        DBConnectionUtil.save(pstmt);

        DBConnectionUtil.disConnection(null, pstmt, connection);
    }


    public ProductVO findProductByProductNo(int productNo) throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM PRODUCT "
            + " WHERE PRODUCT_NO = ? ";

        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, productNo);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        if (!resultSet.next()) {
            throw new DataNotFoundException("해당 상품을 찾을 수 없습니다.");
        }
        ProductVO product = new ProductVO();
        product.setProductNo(resultSet.getInt("PRODUCT_NO"));
        product.setCategory(resultSet.getString("CATEGORY"));
        product.setProductName(resultSet.getString("PRODUCT_NAME"));
        product.setCountry(resultSet.getString("COUNTRY"));
        product.setMaterial(resultSet.getString("MATERIAL"));
        product.setPrice(resultSet.getInt("PRICE"));
        product.setStock(resultSet.getInt("STOCK"));

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return product;
    }

    
    public List<ProductVO> findAllProducts() throws Exception {

        Connection connection = DBConnectionUtil.getConnection();
        String query = "SELECT * FROM PRODUCT";

        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet resultSet = DBConnectionUtil.search(pstmt);

        List<ProductVO> products = new ArrayList<ProductVO>();
        while (resultSet.next()) {
            ProductVO product = new ProductVO();
            product.setProductNo(resultSet.getInt("PRODUCT_NO"));
            product.setCategory(resultSet.getString("CATEGORY"));
            product.setProductName(resultSet.getString("PRODUCT_NAME"));
            product.setCountry(resultSet.getString("COUNTRY"));
            product.setMaterial(resultSet.getString("MATERIAL"));
            product.setPrice(resultSet.getInt("PRICE"));
            product.setStock(resultSet.getInt("STOCK"));
            products.add(product);
        }

        DBConnectionUtil.disConnection(resultSet, pstmt, connection);
        return products;
    }
}
