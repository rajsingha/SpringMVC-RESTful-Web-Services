package com.webservice.mobile.app.ui.controller;

import com.webservice.mobile.app.exceptions.UserServiceException;
import com.webservice.mobile.app.service.UserService;
import com.webservice.mobile.app.shared.dto.UserDTO;
import com.webservice.mobile.app.ui.model.request.UserDetailsRequestModel;
import com.webservice.mobile.app.ui.model.response.ErrorMessages;
import com.webservice.mobile.app.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") // http://localhost:8080/
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String id){
        UserRest returnValue = new UserRest();
        UserDTO userDTO = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDTO,returnValue);
        return returnValue;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE}
            )
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {


        UserRest returnValue = new UserRest();


        if (userDetails.getFirstName().isEmpty()) throw new
                UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails,userDTO);

        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser,returnValue);

        return returnValue;
    }


    @PutMapping
    public String updateUser(){
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(){
        return "delete user was called";
    }


}
