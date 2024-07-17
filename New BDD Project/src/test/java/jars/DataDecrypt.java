package jars;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DataDecrypt {

    private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "aVerySecureKey!!".getBytes();

    public DataDecrypt() {
    }

    public static String decrypt(String var0) throws Exception {
        SecretKeySpec var1 = new SecretKeySpec(keyValue, "AES");
        Cipher var2 = Cipher.getInstance("AES");
        var2.init(2, var1);
        byte[] var3 = Base64.getDecoder().decode(var0);
        byte[] var4 = var2.doFinal(var3);
        return new String(var4);
    }

    public static void main(String[] var0) {
        if (var0.length != 1) {
            System.out.println("Usage: java -jar DataDecryptor.jar <encryptedPassword>");
        } else {
            try {
                String var1 = decrypt(var0[0]);
                System.out.println("Decrypted password: " + var1);
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }
}
