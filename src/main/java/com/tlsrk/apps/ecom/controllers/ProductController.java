package com.tlsrk.apps.ecom.controllers;

import com.tlsrk.apps.ecom.dto.Product;
import com.tlsrk.apps.ecom.dto.User;
import com.tlsrk.apps.ecom.entities.ProductEntity;
import com.tlsrk.apps.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    ResponseEntity<List<Product>> responseList = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    ResponseEntity<Product> response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product savedProduct = productService.addProduct(product);
        return  new ResponseEntity(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("all/")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> productList){
        List<Product> savedProducts =  productService.addProducts(productList);
        return  new ResponseEntity(savedProducts, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        if (productList != null && productList.size() > 0) {
            responseList = new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
        }
        return responseList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        if (product !=null){
            response = new ResponseEntity<Product>(product, HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updateProduct = productService.updateProduct(id, product);
        return  new ResponseEntity(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
       Boolean isDeleted = productService.deleteProduct(id);
       if(isDeleted.booleanValue()){
           return  new ResponseEntity("Product with Id "+id+"Deleted Successfully", HttpStatus.OK);
       }
       else{
           return  new ResponseEntity("Can't process the request right now. Please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }
}
