package com.project.shop.controller;

import java.util.List;

import com.project.shop.service.ProductService;
import com.project.shop.view.ProductView;
import com.project.shop.vo.ProductVO;

public class ProductController {

    private ProductView view = new ProductView();
    private ProductService service = new ProductService();
    public static ProductController instance = new ProductController();


    public void productListView() {
        try {
            List<ProductVO> list = findAllProducts();
            view.productListView(list);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        MainController.instance.start();
    }


    public void productDetailView(int productNo) {
        try {
            ProductVO detail = findProductByProductNo(productNo);
            view.productDetailView(detail);
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        MainController.instance.start();
    }


    public void insertProduct(ProductVO productVO) throws Exception {
        service.insertProduct(productVO);
    }


    public void modifyProduct(ProductVO productVO) throws Exception {
        service.modifyProduct(productVO);
    }

 
    public void deleteProductByProductNo(int productNo) throws Exception {
        service.deleteProductByProductNo(productNo);
    }

    
    public ProductVO findProductByProductNo(int productNo) throws Exception {
        return service.findProductByProductNo(productNo);
    }

    
    public List<ProductVO> findAllProducts() throws Exception {
        return service.findAllProducts();
    }
}
