package com.skripsi.waste_bank.utils;

import java.util.Arrays;
import java.util.regex.Pattern;

public class EmailValidation {
    public String emailValidation(String email){
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        //Compile regular expression to get the pattern
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(email).matches()){
            return email;
        }
        return null;
    }
}
