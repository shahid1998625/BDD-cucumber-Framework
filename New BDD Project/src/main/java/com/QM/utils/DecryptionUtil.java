package com.QM.utils;


public class DecryptionUtil {
    public static String decrypt(String encryptedPassword) throws Exception {
        return DataDecrypt.decrypt(encryptedPassword);
    }
}
