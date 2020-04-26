package com.webservice.mobile.app.shared;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {

    private final Random RANDOM = new Random();
    private final  String ALPHABETS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateUserId(int length){
        return generateRandomString(length);

    }

    private String generateRandomString(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i =0;i<length;i++){
            returnValue.append(ALPHABETS.charAt(RANDOM.nextInt(ALPHABETS.length())));

        }
        return new String(returnValue);
    }
}
