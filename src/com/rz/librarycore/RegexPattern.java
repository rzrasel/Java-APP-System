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
}
