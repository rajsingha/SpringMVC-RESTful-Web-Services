package com.webservice.mobile.app.ui.controller;

import com.webservice.mobile.app.exceptions.UserServiceException;
import com.webservice.mobile.app.service.UserService;
import com.webservice.mobile.app.shared.dto.UserDTO;
import com.webservice.mobile.app.ui.model.request.UserDetailsRequestModel;
import com.webservice.mobile.app.ui.model.response.ErrorMessages;
import com.webservice.mobile.app.ui.model.response.OperationStatusModel;
import com.webservice.mobile.app.ui.model.response.RequestOperationStatus;
import com.webservice.mobile.app.ui.model.response.UserRest;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users") // http://localhost:8080/mobile-app-ws/users
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "The Get User Details Web Service Endpoint",
    notes = "This Web Service Endpoint returns User Details. User public user id in URL path." +
            "For Example: /user/<User Id here>")
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

        if (userDetails.getFirstName().isEmpty() ||
                userDetails.getLastName().isEmpty() ||
                userDetails.getEmail().isEmpty() ||
                userDetails.getPassword().isEmpty()
        ) throw new
                UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails,userDTO);

        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser,returnValue);

        return returnValue;
    }


    @PutMapping(path = "/{id}",consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails){

        UserRest returnValue = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails,userDTO);
        UserDTO updateUser = userService.updateUser(id,userDTO);
        BeanUtils.copyProperties(updateUser,returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel deleteUser(@PathVariable String id){
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        return returnValue;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name ="authorization",
                    value ="${userController.authorizationHeader.description}",
                    paramType = "header")
    })
    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value = "page",defaultValue = "0")int page,
                                   @RequestParam(value = "limit",defaultValue = "25")int limit)
    {

        List<UserRest> returnValue = new ArrayList<>();

        List<UserDTO> users =userService.getUsers(page,limit);

        for (UserDTO userDTO: users){
            UserRest userRest = new UserRest();
            BeanUtils.copyProperties(userDTO,userRest);
            returnValue.add(userRest);
        }

        return returnValue;
    }


}
