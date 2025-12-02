package com.tlsrk.apps.ecom.repo;

import com.tlsrk.apps.ecom.dto.CartItem;
import com.tlsrk.apps.ecom.entities.CartEntity;
import com.tlsrk.apps.ecom.entities.ProductEntity;
import com.tlsrk.apps.ecom.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long>{
    Optional<CartEntity> findByUserAndProduct(UserEntity user, ProductEntity product);
    void deleteByUserAndProduct(UserEntity user, ProductEntity product);
    List<CartEntity> findByUser(UserEntity user);
    void deleteByUser(UserEntity user);
}
