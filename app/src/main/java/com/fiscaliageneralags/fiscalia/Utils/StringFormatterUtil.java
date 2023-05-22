package com.fiscaliageneralags.fiscalia.Utils;

/**
 * Created by ERodriguezF on 02/03/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class StringFormatterUtil {

    /**
     * Convert a string to Camel Case if its higher than 3 characters
     * @param str2conv String to Convert
     * @return
     */
    public static String convertStringToFUL(String str2conv){
        if(str2conv == null || str2conv.isEmpty()) return null;
        String[] strArray = str2conv.toLowerCase().split(" ");
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (String s : strArray) {
            if(first && !s.isEmpty()){
                first = false;
                String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                builder.append(cap + " ");
            }
            else if(s.equals("san") || s.length() > 3 ){
                String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                builder.append(cap + " ");
            }
            else{
                builder.append(s + " ");
            }
        }

        return builder.substring(0,builder.length());//.toString();
    }
}
