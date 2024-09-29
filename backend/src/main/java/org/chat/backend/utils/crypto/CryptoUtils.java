package org.chat.backend.utils.crypto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HexFormat;

public class CryptoUtils {

    public static String generateSecureRandomBytes(Integer size) throws NoSuchAlgorithmException {

        // Binary to Hex conversion

        var bytes = new byte[size / 2];

        SecureRandom
                .getInstance("NativePRNG")
                .nextBytes(bytes);

        return HexFormat
                .of()
                .formatHex(bytes);
    }

    public static String generateSha256Hash(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {

        var bytes = text.getBytes("UTF-8");
        var hash = MessageDigest
                .getInstance("SHA-256")
                .digest(bytes);

        return HexFormat
                .of()
                .formatHex(hash);
    }

}
