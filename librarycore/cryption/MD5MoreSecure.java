/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.cryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author developer
 */
public class MD5MoreSecure {

    public static String usage(String argPasswordToHash) {
        String generatedPassword = null;
        //String passwordToHash = "password";
        String passwordToHash = argPasswordToHash;
        try {
            byte[] salt = getSalt();
            generatedPassword = getSecurePassword(passwordToHash, salt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5MoreSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(MD5MoreSecure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedPassword;
    }
    
    public static String getCryption(String argPasswordToHash) {
        String generatedPassword = null;
        //String passwordToHash = "password";
        String passwordToHash = argPasswordToHash;
        try {
            byte[] salt = getSalt();
            generatedPassword = getSecurePassword(passwordToHash, salt);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(MD5MoreSecure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(MD5MoreSecure.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedPassword;
    }

    public static String getSecurePassword(String argPasswordToHash, byte[] argSalt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            messageDigest.update(argSalt);
            //Get the hash's bytes
            byte[] bytes = messageDigest.digest(argPasswordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        //secureRandom.nextBytes(salt);
        //return salt
        System.out.println("com.rz.libs.MD5MoreSecure.getSalt(): " + salt);
        return salt;
    }
}
