package com.webservice.mobile.app.service.impl;

import com.webservice.mobile.app.UserRepository;
import com.webservice.mobile.app.io.entity.UserEntity;
import com.webservice.mobile.app.service.UserService;
import com.webservice.mobile.app.shared.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {


        if (userRepository.findUserByEmail(userDTO.getEmail()) !=null)
            throw new RuntimeException("Record Already Exists");


        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO,userEntity);

        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("testUserId");

        UserEntity storedUSerDeatils =userRepository.save(userEntity);
        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUSerDeatils,returnValue);

        return returnValue;
    }
}
