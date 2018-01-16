/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

/**
 *
 * @author Rz Rasel
 */
public class CommentGenerate {

    public static void main(String args[]) {
        char c = (char) 8213;
        System.out.println(c);
        onLine("String format", 0);
        onLine("GETNAME METHOD RETURNS STRING", 0);
    }

    public static String onLine(String argText, int commentLevel) {
        int length = 48;
        char cCharacter = (char) 8213;
        String starting = "";
        String finalString = "";
        /*if (argText.isEmpty()) {
            //return;
        }
        starting = "//|" + String.format("%1$-" + 4 + "s", "").replace(' ', cCharacter) + "|";
        //starting = "//|";
        finalString = argText.replaceAll("\\s+", " ").toUpperCase();
        String[] splited = finalString.split("\\s+");
        length = (length + (splited.length - 1) * 7) - (finalString.length() + commentLevel * 4);
        /*int addValue = (splited.length - 1) * 7;
        length = length + addValue;*--/
        finalString = argText.replaceAll("\\s+", "@space@").toUpperCase();
        //System.out.println("OUT: " + length);
        if (length > 0) {
            finalString = String.format("%1$-" + length + "s", finalString).replace(' ', cCharacter) + "|";
            //finalString += "88";
            System.out.println("Test: " + finalString + " LEN: " + length);
        }
        finalString = finalString.replaceAll("@space@+", " ").toUpperCase();
        finalString = finalString.replaceAll("@SPACE@+", " ").toUpperCase();*/
        starting = "//|" + String.format("%1$-" + 4 + "s", "").replace(' ', cCharacter) + "|";
        finalString = argText.replaceAll("\\s+", " ").toUpperCase();
        length = length - (finalString.length() + commentLevel * 4);
        if (length > 0) {
            String repeated = "" + cCharacter;
            repeated = String.format("%0" + length + "d", 0).replace("0", repeated);
            //System.out.println("STR: " + repeated);
            finalString += repeated + "|";
        }
        finalString = starting + finalString;
        System.out.println(finalString + " Len: " + length + " Add: ");
        return finalString;
    }

    public static String inLine(String argText) {
        char cCharacter = (char) 8213;
        String starting = "";
        String finalString = "";
        if (argText.isEmpty()) {
            //return;
        }
        //starting = "//|" + String.format("%1$-" + 4 + "s", "").replace(' ', cCharacter) + "|";
        starting = "//|";
        finalString = argText.replaceAll("\\s+", " ").toUpperCase();
        finalString = starting + finalString;
        System.out.println(finalString + " Len: ");
        return finalString;
    }
}
//System.out.println(String.format("%1$-" + 20 + "s", "Hi").replace(' ', '―'));
