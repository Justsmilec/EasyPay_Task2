package com.example.easypay_task;

import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityHandler {

    private static String IV = "IV_VALUE_16_BYTE";
    private static String PASSWORD = "some-hash-password";
    private static String SALT = "some-hash-salt";

    public static String encryptAndEncode(String raw) {
        try {
            Cipher c = getCipher(Cipher.ENCRYPT_MODE);
            byte[] encryptedVal = c.doFinal(getBytes(raw));
            String s = Base64.encodeToString(encryptedVal, Base64.DEFAULT);
            return s;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    public static String decodeAndDecrypt(String encrypted) throws Exception {
        byte[] decodedValue = Base64.decode(getBytes(encrypted),Base64.DEFAULT);
        Cipher c = getCipher(Cipher.DECRYPT_MODE);
        byte[] decValue = c.doFinal(decodedValue);
        return new String(decValue);
    }

    private String getString(byte[] bytes) throws UnsupportedEncodingException {
        return new String(bytes, "UTF-8");
    }

    private static byte[] getBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8");
    }

    private static Cipher getCipher(int mode) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = getBytes(IV);
        c.init(mode, generateKey(), new IvParameterSpec(iv));
        return c;
    }

    private static Key generateKey() throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        char[] password = PASSWORD.toCharArray();
        byte[] salt = getBytes(SALT);

        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        byte[] encoded = tmp.getEncoded();
        return new SecretKeySpec(encoded, "AES");
    }


//    public SecretKeySpec key;
//    public SecurityHandler(String password) throws Exception {
//            this.key = generateKey(password);
//
//    }
//
//    public  String encrypt(String str) throws Exception {
//        //SecretKeySpec key = generateKey(password);
//        Cipher c = Cipher.getInstance("AES");
//        c.init(Cipher.ENCRYPT_MODE,key);
//        byte[] encVal = c.doFinal(str.getBytes());
//        String encryptedString = Base64.encodeToString(encVal,Base64.DEFAULT);
//        System.out.println("----------ENCRYPTED : "+  encryptedString);
//        return encryptedString;
//
//    }
//
//    private static SecretKeySpec generateKey(String password) throws Exception {
//        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        byte[] bytes = password.getBytes("UTF-8");
//        digest.update(bytes,0,bytes.length);
//        byte[] key = digest.digest();
//        SecretKeySpec secretKeySpec = new SecretKeySpec(key,"AES");
//        return secretKeySpec;
//
//
//    }
//
//    public  String decrypt(String str) throws Exception {
//        //SecretKeySpec key = generateKey(password);
//        Cipher c = Cipher.getInstance("AES");
//        c.init(Cipher.DECRYPT_MODE,key);
//
//        byte[] decryptedString = Base64.decode(str,Base64.DEFAULT);
//        byte[] decvali = c.doFinal(decryptedString);
//        Log.d("DECRYPTED", "decrypt: "+ decvali);
//        String decryptedValue = new String(decvali);
//        System.out.println("----------DECCRYPTED : "+  decryptedValue);
//
//        return decryptedValue;
//
//
//    }
}
