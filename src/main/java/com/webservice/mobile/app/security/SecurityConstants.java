package com.webservice.mobile.app.security;

import com.webservice.mobile.app.SpringApplicationContext;


public class SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; //Validity  10 Days
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret(){
        AppProperties appProperties = (AppProperties) SpringApplicationContext.
                getBean("AppProperties");
        return appProperties.getTokenSecret();
    }
}
