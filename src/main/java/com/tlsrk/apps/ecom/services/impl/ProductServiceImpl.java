package com.tlsrk.apps.ecom.services.impl;

import com.tlsrk.apps.ecom.dto.Product;
import com.tlsrk.apps.ecom.entities.ProductEntity;
import com.tlsrk.apps.ecom.entities.UserEntity;
import com.tlsrk.apps.ecom.repo.ProductRepository;
import com.tlsrk.apps.ecom.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(this::mapProductEntityToProduct).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).map(this::mapProductEntityToProduct).orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        return mapProductEntityToProduct(productRepository.save(mapProductToProductEntity(product)));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
       return productRepository.findById(id).map(exisitingProduct -> {
            product.setId(id);
            ProductEntity entity = mapProductToProductEntity(product);
            productRepository.save(entity);
            return mapProductEntityToProduct(entity);
        }).orElse(null);
    }

    @Override
    public boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Product> addProducts(List<Product> productList) {
        List<ProductEntity> productEntities = productList.stream().map(this::mapProductToProductEntity).collect(Collectors.toList());
        List<ProductEntity> savedEntities = productRepository.saveAll(productEntities);
        return savedEntities.stream().map(this::mapProductEntityToProduct).collect(Collectors.toList());
    }

    private Product mapProductEntityToProduct(ProductEntity entity) {
        if(entity!=null && entity.getInStock()){
            Product product = new Product();
            product.setId(entity.getId());
            product.setProductName(entity.getProductName());
            product.setDescription(entity.getDescription());
            product.setCategory(entity.getCategory());
            product.setPrice(entity.getPrice());
            if(entity.getQuantity()>0){
                product.setInStock(true);
            }else{
                product.setInStock(false);
            }
            product.setQuantity(entity.getQuantity());
            product.setImageUrl(entity.getImageUrl());
            product.setOwnerCompany(entity.getOwnerCompany());
            return product;
        }else {
            return null;
        }
    }

    private ProductEntity mapProductToProductEntity(Product product) {
        ProductEntity entity = new ProductEntity();
        if(product.getId()!=null && product.getId()>0){
            entity.setId(product.getId());
        }
        entity.setProductName(product.getProductName());
        entity.setDescription(product.getDescription());
        entity.setCategory(product.getCategory());
        entity.setPrice(product.getPrice());
        entity.setQuantity(product.getQuantity());
        entity.setImageUrl(product.getImageUrl());
        entity.setOwnerCompany(product.getOwnerCompany());
        if(product.getQuantity()>0){
            entity.setInStock(true);
        }else{
            entity.setInStock(false);
        }
        return entity;
    }
}
