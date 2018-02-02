/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rz Rasel
 */
public class FieldValidator {

    private Pattern patternRegex;
    private Matcher regexMatcher;
    public List<String> messages = new ArrayList<String>();

    public FieldValidator() {
        messages.clear();
        messages = new ArrayList<String>();
    }

    public boolean isNumbersAndDots(String argValue) {
        //^[+-]?([0-9]*[.])?[0-9]+$
        patternRegex = Pattern.compile("^\\d+(\\.\\d+)*$");
        //regexMatcher = patternRegex.matcher("1.0.001");
        regexMatcher = patternRegex.matcher(argValue);
        if (regexMatcher.find()) {
            //System.out.println("Valid_PACKAGE");
            return true;
        } else {
            //System.out.println("inValid_PACKAGE");
            return false;
        }
    }

    public boolean isRealNumber(String argValue) {
        patternRegex = Pattern.compile("^[+-]?([0-9]*[.])?[0-9]+$");
        regexMatcher = patternRegex.matcher(argValue);
        if (regexMatcher.find()) {
            //System.out.println("Valid_NUMBER");
            return true;
        } else {
            //System.out.println("inValid_NUMBER");
            return false;
        }
    }

    public boolean isNumber(String argValue) {
        //patternRegex = Pattern.compile("\\d+");
        patternRegex = Pattern.compile("^[0-9]*$");
        regexMatcher = patternRegex.matcher(argValue);
        if (regexMatcher.find()) {
            System.out.println("Valid_NUMBER");
            return true;
        } else {
            System.out.println("inValid_NUMBER");
            return false;
        }
    }
    public boolean isNumberNP(String argValue) {
        //negative or positive
        patternRegex = Pattern.compile("^[+-]?\\d+$");
        regexMatcher = patternRegex.matcher(argValue);
        if (regexMatcher.find()) {
            System.out.println("Valid_NUMBER");
            return true;
        } else {
            System.out.println("inValid_NUMBER");
            return false;
        }
    }

    public boolean isValidPackage(String argValue) {
        patternRegex = Pattern.compile("^[a-z][a-z0-9_]*(\\.[a-z0-9_]+)+$");
        regexMatcher = patternRegex.matcher(argValue);
        //Matcher regexMatcher = regex.matcher("com.abc.texr");
        if (regexMatcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
