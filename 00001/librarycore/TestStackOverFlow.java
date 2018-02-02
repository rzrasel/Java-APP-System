/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Rz Rasel
 */
public class TestStackOverFlow {

    public int gen(int userLen) {
        if (userLen <= 0) {
            return 0;
        }
        int randLen = 1;
        for (int i = 0; i < userLen; i++) {
            randLen = 10 * randLen;
        }
        randLen--;
        Random random = new Random();
        return random.nextInt(randLen);
    }

    public static void main(String[] args) {
        TestStackOverFlow ob = new TestStackOverFlow();
        System.out.println("Enter Length: ");
        Scanner keyboard = new Scanner(System.in);
        int noOfDigits = Integer.valueOf(keyboard.next());
        if (noOfDigits <= 0) {
            return;
        }
        System.out.println("VALUE: " + ob.gen(noOfDigits));
    }
}
