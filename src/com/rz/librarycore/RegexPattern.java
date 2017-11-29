/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rz Rasel
 */
public class RegexPattern {

    private Pattern patternRegex;
    private Matcher regexMatcher;

    public static String onRemovingWhiteSpace(String argValue) {
        return argValue.replaceAll("\\s+", "");
    }

    public static String onRemovingWhiteSpace(String argValue, String argReplaceBy) {
        return argValue.replaceAll("\\s+", argReplaceBy);
    }

    public static String onExtractNumbers(String argValue) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern patternRegex;
        Matcher regexMatcher;
        patternRegex = Pattern.compile("\\d+");
        regexMatcher = patternRegex.matcher(argValue);
        while (regexMatcher.find()) {
            //System.out.println("while: " + regexMatcher.group());
            stringBuilder.append(regexMatcher.group());
        }
        return stringBuilder.toString();
    }

    public static String onExtractRealNumbers(String argValue) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern patternRegex;
        Matcher regexMatcher;
        patternRegex = Pattern.compile("([+-]?\\d*\\.?\\d*)");
        regexMatcher = patternRegex.matcher(argValue);
        while (regexMatcher.find()) {
            //System.out.println("while: " + regexMatcher.group());
            stringBuilder.append(regexMatcher.group());
        }
        System.out.println("REGEX_VALUE: " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    public static String[] onSplitCamelCaseWords(String argValue) {
        /*String str = "ThisIsAString";
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                res += " " + Character.toLowerCase(ch);
            } else {
                res += ch;
            }
        }
        System.out.println("VALUES: " + res);*/
        //Java: Split string when an uppercase letter is found
        /*String strCamel = "thisIsMyString";
        String[] camelCaseWords1 = strCamel.split("(?=[A-Z])");
        String[] camelCaseWords2 = strCamel.split("(?=\\p{Upper})");
        String[] camelCaseWords3 = strCamel.split("(?=\\p{Lu})");
        String[] camelCaseWords4 = strCamel.split("(?<=.)(?=\\p{Lu})");
        System.out.println("VALUES: " + Arrays.toString(camelCaseWords1));
        System.out.println("VALUES: " + Arrays.toString(camelCaseWords2));
        System.out.println("VALUES: " + Arrays.toString(camelCaseWords3));
        System.out.println("VALUES: " + Arrays.toString(camelCaseWords4));*/
        return argValue.split("(?<=.)(?=\\p{Lu})");
    }

    public static String toCamelCase(String argValue) {
        argValue = argValue.replaceAll("\\s+", " ");
        String[] parts = argValue.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            if (part != null && part.trim().length() > 0) {
                camelCaseString = camelCaseString + toProperCase(part);
            } else {
                camelCaseString = camelCaseString + part + " ";
            }
        }
        return camelCaseString;
    }

    public static String toProperCase(String argValue) {
        if (argValue == null) {
            return null;
        }
        argValue = argValue.replaceAll("\\s+", " ");
        final StringBuilder ret = new StringBuilder(argValue.length());
        for (final String word : argValue.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length() == argValue.length())) {
                ret.append(" ");
            }
        }
        return ret.toString();
    }
}
