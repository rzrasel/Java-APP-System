/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Rz Rasel
 */
public class OnFileWrite {

    public static void main(String args[]) {
        /*FieldValidator fieldValidator = new FieldValidator();
        fieldValidator.isNumberNP("-900001");
        RegexPattern.onExtractRealNumbers("as-+dfs-90df.fadd56sdf.adfasdf3455");
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("PI = %f%n", Math.PI);
        System.out.print(sbuf.toString());*/
        onWrite("Test.txt", "Test Data");
    }

    public static void onWrite(String argFile, String argData) {
        String encoding = "UTF-8";
        File file = null;
        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;
        //writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("filename.txt"), "utf-8")); writer.write("Something");
        try {
            file = new File(argFile);
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, encoding);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(argData);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                /*if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }*/
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(OnFileWrite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
/*
https://stackoverflow.com/questions/12001037/regex-for-validating-only-numbers-and-dots
https://stackoverflow.com/questions/12643009/regular-expression-for-floating-point-numbers
 */