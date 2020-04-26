package com.webservice.mobile.app.exceptions;

import com.webservice.mobile.app.service.UserService;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 134877110171435607L;

    public UserServiceException(String message){
        super(message);
    }
}
