package com.tlsrk.apps.ecom.repo;

import com.tlsrk.apps.ecom.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
