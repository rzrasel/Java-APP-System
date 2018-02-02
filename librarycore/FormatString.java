/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rz Rasel
 */
public class FormatString {

    //String output = String.format("%s = %d", "joe", 35);
    //SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String args[]) {
        onPhone();
    }

    public static void onPhone() {
        String strPhoneNumber = "9998886666";
        String onlyDidgits = strPhoneNumber.replaceAll("\\D+", "");
        Pattern phoneNumber = Pattern.compile("(\\d{3})(\\d{3})(\\d{4})");
        Matcher matcher = phoneNumber.matcher(onlyDidgits);
        if (matcher.matches()) {
            System.out.println("(" + matcher.group(1) + ")-" + matcher.group(2) + "-" + matcher.group(3));
            System.out.println(matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3));
        }
        /*
        String input = Integer.toString( 1234567890 );
        String output = "(" + input.substring( 0,3 ) + ") " + input.substring( 3,6 ) + "-" + input.substring( 6,10 );
        System.out.println("result=" + output);
        */
    }
}
