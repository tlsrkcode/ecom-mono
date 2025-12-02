package com.tlsrk.apps.ecom.repo;

import com.tlsrk.apps.ecom.entities.CartEntity;
import com.tlsrk.apps.ecom.entities.OrderEntity;
import com.tlsrk.apps.ecom.entities.ProductEntity;
import com.tlsrk.apps.ecom.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Long>{
}
