package com.tlsrk.apps.ecom.services;

import com.tlsrk.apps.ecom.dto.User;

import java.math.BigInteger;
import java.util.List;


public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    User updateUser(Long id, User user);
    boolean deleteUser(Long id);
}
