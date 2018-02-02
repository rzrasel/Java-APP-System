/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rz Rasel
 */
public class OnFileRead {

    public static void main(String args[]) {
        String fileData = "";
        fileData = onRead("test.txt");
        System.out.println("DEBUG_PRINT:" + fileData);
        /*Matcher regexMatcher;
        //(?is)\b(?:from|into|update)\s+(\w+)
        Pattern pattern = Pattern.compile("(?i)\\b(?:exists|from|join)\\s+([a-zA-Z0-9_$#-]*\\.?\\s*(?:[a-zA-Z0-9_]+)*)");
        regexMatcher = pattern.matcher(fileData);
        while (regexMatcher.find()) {
            System.out.println("DEBUG_PRINT:" + regexMatcher.group(1));
        }*/
        String query = "Select uname AS name, hgt AS height, wgt AS weight from table1";
        Pattern p = Pattern.compile("\\s*\\w+,");
        Pattern p1 = Pattern.compile("\\s+\\w+\\s+from");
        Matcher m = p.matcher(query);
        Matcher m1 = p1.matcher(query);
        String colsOnly = "";
        while (m.find()) {
            colsOnly += (m.group().trim());
        }
        System.out.println("DEBUG_PRINT:" + colsOnly);
        while (m1.find()) {
            colsOnly += (m1.group().substring(0, m1.group().length() - 4).trim());
        }
        System.out.println("DEBUG_PRINT::" + colsOnly);
    }

    public static String onRead(String argFile) {
        String fileData = "";
        File file = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            file = new File(argFile);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String line;
            line = bufferedReader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            fileData = sb.toString();
        } catch (IOException ex) {
            Logger.getLogger(OnFileRead.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(OnFileRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return fileData;
    }
}
