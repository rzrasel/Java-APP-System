/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rz.librarycore.cryption;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 *
 * @author developer
 */
public class PasswordEncryptionService {

    public static boolean authenticate(String argAttemptedPassword, byte[] argEncryptedPassword, byte[] argSalt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(argAttemptedPassword, argSalt);

        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(argEncryptedPassword, encryptedAttemptedPassword);
    }

    public static byte[] getEncryptedPassword(String argPassword, byte[] argSalt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 20000;

        KeySpec keySpec = new PBEKeySpec(argPassword.toCharArray(), argSalt, iterations, derivedKeyLength);

        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);

        return secretKeyFactory.generateSecret(keySpec).getEncoded();
        /*SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");*/
    }

    public static byte[] generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        secureRandom.nextBytes(salt);

        return salt;
    }

    public static byte[] encrypt(byte[] data, char[] password, byte[] salt, int noIterations) {
        try {
            String method = "PBEWithMD5AndTripleDES";
            SecretKeyFactory kf = SecretKeyFactory.getInstance(method);
            PBEKeySpec keySpec = new PBEKeySpec(password);
            SecretKey key = kf.generateSecret(keySpec);
            Cipher ciph = Cipher.getInstance(method);
            PBEParameterSpec params = new PBEParameterSpec(salt, noIterations);
            return ciph.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Spurious encryption error");
        }
    }
}
