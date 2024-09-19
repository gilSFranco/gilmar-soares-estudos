package com.compasso.rest_spring.entitys;

import com.compasso.rest_spring.exceptions.UnsupportedMathOperationException;

public class ValidatingParameters {

    private ValidatingParameters() {}

    public static Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;

        String number = strNumber.replace(",", ".");

        if(!isNumeric(strNumber)) {
            throw new UnsupportedMathOperationException("Please set a numeric value!");
        }

        if(isNumeric(number)) return Double.parseDouble(number);

        return 0D;
    }

    public static boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replace(",", ".");

        return number.matches("[-+]?\\d*\\.?\\d+");
    }
}
