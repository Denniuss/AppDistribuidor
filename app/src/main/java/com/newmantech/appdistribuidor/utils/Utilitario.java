package com.newmantech.appdistribuidor.utils;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitario {

    public static String baseUrl = "http://192.168.1.60:8077/";

    public static boolean isInteger(String pNumber)
    {
        try {
            double vDecimal = Double.parseDouble(pNumber);
            if(vDecimal == (int) vDecimal){
                return true;
            }else{
                return false;
            }
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isNumeric(String pNumber)
    {
        try {
            double number = Double.parseDouble(pNumber);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }

    public static boolean isEmail(String pEmail) {
        //^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,6}$
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(pEmail);
        return matcher.find();
    }

    public static double roundDecimals(double d, int decimals)
    {
        String pattern = "#0.";
        for(int i=0; i<decimals; i++){
            pattern = pattern + "#";
        }
        DecimalFormat twoDForm = new DecimalFormat(pattern);
        return Double.valueOf(twoDForm.format(d));
    }
}
