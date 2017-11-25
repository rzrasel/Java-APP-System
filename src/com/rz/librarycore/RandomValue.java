/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore;

import java.util.Random;

/**
 *
 * @author developer
 */
public class RandomValue {

    public static String getRandId(int argMin, int argMax) {
        String randId = "";
        randId = (int) (System.currentTimeMillis() / 1000) + "" + randInt(argMin, argMax);
        return randId;
    }

    public static int randInt(int argMin, int argMax) {
        Random random = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = random.nextInt((argMax - argMin) + 1) + argMin;
        return randomNum;
    }
}
