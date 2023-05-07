package com.project.shop.service;

import java.util.List;
import java.util.Objects;

import com.project.shop.dao.ProductDAO;
import com.project.shop.exception.DataNotFoundException;
import com.project.shop.vo.ProductVO;

public class ProductService {
    private final ProductDAO dao = new ProductDAO();

    
    public void insertProduct(ProductVO productVO) throws Exception {
        dao.insertProduct(productVO);
    }

    
    public void modifyProduct(ProductVO productVO) throws Exception {

        if (Objects.isNull(findProductByProductNo(productVO.getProductNo()))) {
            throw new DataNotFoundException("존재하지 않는 상품입니다.");
        }
        dao.modifyProduct(productVO);
    }

  
    public void deleteProductByProductNo(int productNo) throws Exception {

        if (Objects.isNull(findProductByProductNo(productNo))) {
            throw new DataNotFoundException("존재하지 않는 상품입니다.");
        }
        dao.deleteProductByProductNo(productNo);
    }

   
    public ProductVO findProductByProductNo(int productNo) throws Exception {
        return dao.findProductByProductNo(productNo);
    }

    
    public List<ProductVO> findAllProducts() throws Exception {
        return dao.findAllProducts();
    }
}
