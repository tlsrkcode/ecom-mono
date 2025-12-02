package com.tlsrk.apps.ecom.services;

import com.tlsrk.apps.ecom.dto.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProductService {

    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(Product Product);
    Product updateProduct(Long id, Product Product);
    boolean deleteProduct(Long id);
    List<Product> addProducts(List<Product> productList);
}
