package com.tlsrk.apps.ecom.repo;

import com.tlsrk.apps.ecom.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItemEntity, Long>{
}
