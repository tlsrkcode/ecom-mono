package com.tlsrk.apps.ecom.services.impl;

import com.tlsrk.apps.ecom.dto.Address;
import com.tlsrk.apps.ecom.dto.User;
import com.tlsrk.apps.ecom.entities.AddressEntity;
import com.tlsrk.apps.ecom.entities.UserEntity;
import com.tlsrk.apps.ecom.enums.UserRole;
import com.tlsrk.apps.ecom.repo.UserRepository;
import com.tlsrk.apps.ecom.services.UserService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        /**
        List<User> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.forEach(userEntity -> {
            users.add(mapUserEntityToUser(userEntity));
        });
        return users;
         **/
        return userRepository.findAll().stream().map(this::mapUserEntityToUser).collect((Collectors.toList()));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).map(this::mapUserEntityToUser).orElse(null);
    }

    @Override
    public User addUser(User user) {
        UserEntity enity = mapUserToUserEntity(user);
        return mapUserEntityToUser(userRepository.save(enity));
    }

    @Override
    public User updateUser(Long userId, User user) {
      return userRepository.findById(userId).map(existingUser ->  {
           user.setId(String.valueOf(userId));
           UserEntity entity = mapUserToUserEntity(user);
           userRepository.save(entity);
           return mapUserEntityToUser(entity);
       }).orElse(null);
    }

    @Override
    public boolean deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        if(userEntity!=null && userEntity.getId().equals(userId)){
            userRepository.delete(userEntity);
            return true;
        }
        return false;
    }

    private User mapUserEntityToUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        } else {
            User user = new User();
            user.setId(String.valueOf(userEntity.getId()));
            user.setFirstName(userEntity.getFirstName());
            user.setLastName(userEntity.getLastName());
            user.setEmail(userEntity.getEmail());
            user.setPhone(userEntity.getPhone());
            user.setUserRole(userEntity.getUserRole());
            if (userEntity.getAddress() != null) {
                Address address = new Address();
                address.setAddressType(userEntity.getAddress().getAddressType());
                address.setStreet(userEntity.getAddress().getStreet());
                address.setCity(userEntity.getAddress().getCity());
                address.setZip(userEntity.getAddress().getZip());
                address.setState(userEntity.getAddress().getState());
                address.setCountry(userEntity.getAddress().getCountry());
                user.setAddress(address);
            }
            return user;
        }
    }

    private UserEntity mapUserToUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        if(StringUtils.isNotEmpty(user.getId())){
            userEntity.setId(Long.parseLong(user.getId()));
        }
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPhone(user.getPhone());
        userEntity.setUserRole(user.getUserRole());
        if (user.getAddress() != null) {
        AddressEntity addressEntity = new AddressEntity();
            addressEntity.setAddressType(user.getAddress().getAddressType());
            addressEntity.setStreet(user.getAddress().getStreet());
            addressEntity.setCity(user.getAddress().getCity());
            addressEntity.setZip(user.getAddress().getZip());
            addressEntity.setState(user.getAddress().getState());
            addressEntity.setCountry(user.getAddress().getCountry());
            userEntity.setAddress(addressEntity);
        }
        return userEntity;
    }

}
