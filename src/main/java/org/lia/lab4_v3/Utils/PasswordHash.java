package org.lia.lab4_v3.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    MessageDigest digester;

    public PasswordHash() throws NoSuchAlgorithmException {

        digester = MessageDigest.getInstance("SHA-1");

    }

    public String hash(String password) {
        byte[] passwordByte = digester.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : passwordByte) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

}
