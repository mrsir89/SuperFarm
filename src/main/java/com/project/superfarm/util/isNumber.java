package com.project.superfarm.util;

public class isNumber {

    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isStringInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }


    public static boolean isStringLong(String s){
        try{
            Long.parseLong(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }


}
