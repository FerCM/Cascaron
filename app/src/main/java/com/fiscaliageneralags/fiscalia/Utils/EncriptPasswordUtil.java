package com.fiscaliageneralags.fiscalia.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ERodriguezF on 17/01/2018.
 * @author ERodriguezF
 * @version 1.18
 */

public class EncriptPasswordUtil {

    /**
     * Encripts the given String using MD5 format
     */
    public static String md5(final String s) throws NoSuchAlgorithmException{
        final String MD5 = "MD5";

        // Create MD5 Hash
        MessageDigest digest = MessageDigest
                .getInstance(MD5);
        digest.update(s.getBytes());
        byte[] messageDigest = digest.digest();

        // Create Hex String
        StringBuilder hexString = new StringBuilder();
        for (byte aMessageDigest : messageDigest) {
            String h = Integer.toHexString(0xFF & aMessageDigest);
            while (h.length() < 2)
                h = "0" + h;
            hexString.append(h);
        }
        return hexString.toString();
    }

}
