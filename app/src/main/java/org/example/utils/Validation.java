package org.example.utils;

import org.example.models.UserInfoDto;

import java.util.regex.Pattern;

public class Validation {


    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$");



    private static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private static boolean isStrongPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }


    public static Boolean validateUserAttributes(UserInfoDto userInfoDto){
        String email = userInfoDto.getEmail();
        String password = userInfoDto.getPassword();
        return isValidEmail(email) && isStrongPassword(password);
    }
}
