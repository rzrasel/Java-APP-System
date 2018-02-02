/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.guiform;

import java.awt.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rz Rasel 2017-11-22.
 */
public class FormFieldMeta implements Comparable<FormFieldMeta> {

    public int serial;
    public String name;
    public Component component;
    //public String type;
    public String value = "";

    public FormFieldMeta() {
    }

    public FormFieldMeta(int argSerial, String argName, Component argComponent, String argValue) {
        this.serial = argSerial;
        this.name = argName;
        this.component = argComponent;
        this.value = argValue;
    }

    @Override
    public int compareTo(FormFieldMeta argFieldMeta) {
        if (this.serial == argFieldMeta.serial) {
            return 0;
        } else if (this.serial > argFieldMeta.serial) {
            return 1;
        } else {
            return -1;
        }
    }

    public int onIntRegexMatcher(String argStr) {
        int retVal = -1;
        if (argStr.isEmpty()) {
            return retVal;
        }
        //int number = Integer.parseInt(matcher1.group());
        //Pattern regex = Pattern.compile(".*\\d+.*");
        //Pattern regex = Pattern.compile("([0-9])");
        //Pattern regex = Pattern.compile("(.)*(\\d)(.)*");
        //String re1 = "^([+-]?\\d*\\.?\\d*)$";
        StringBuilder stringBuilder = new StringBuilder();
        Pattern regex = Pattern.compile("\\d+");
        Matcher regexMatcher = regex.matcher(argStr);
        while (regexMatcher.find()) {
            //System.out.println("while: " + regexMatcher.group());
            stringBuilder.append(regexMatcher.group());
        }
        System.out.println("REGEX_VALUE: " + stringBuilder.toString());
        retVal = Integer.valueOf(stringBuilder.toString());
        return retVal;
    }
}
/*
https://beginnersbook.com/2013/12/java-arraylist-of-object-sort-example-comparable-and-comparator/




//int number = Integer.parseInt(matcher1.group());
//Pattern regex = Pattern.compile(".*\\d+.*");
//Pattern regex = Pattern.compile("([0-9])");
//Pattern regex = Pattern.compile("(.)*(\\d)(.)*");
String re1 = "^([+-]?\\d*\\.?\\d*)$";
Pattern regex = Pattern.compile("\\d+");
Matcher regexMatcher = regex.matcher("Hello This is (78Java) Not (.NET6 66)");
while (regexMatcher.find()) {
    System.out.println("while: " + regexMatcher.group());
}
if (regexMatcher.find()) {
    //System.out.println("Hello " + regexMatcher.group());
    //System.out.println("Hello " + regexMatcher.matches());
    while (regexMatcher.find()) {
        System.out.println("while " + regexMatcher.group());
        for (int i = 1; i <= regexMatcher.groupCount(); i++) {
            // matched text: regexMatcher.group(i)
            // match start: regexMatcher.start(i)
            // match end: regexMatcher.end(i)
            System.out.println("for " + regexMatcher.group(1));
        }
    }
}
 */
