package RedPay;

import org.apache.commons.codec.binary.*;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

    // Verifies that 256-bit keys can be used.
    public static boolean validateClientJDK()
    {
        try {
            int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
            if (maxKeyLen == 128)
            {
                System.out.println("Max Key Length: " + maxKeyLen + " bits");
                System.out.println("Your installed JDK restricts key sizes to 128 bits. Our software requires the use of 256 bit keys for encryption.");
                System.out.println("To remove this restriction, please download the JCE Unlimited Strength Jurisdiction Policy Files from http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html");
                System.out.println("Extract the .zip file and place the .jar files in your Java\\jdk\\jre\\lib\\security folder. This will replace the pre-installed files.");
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (NoSuchAlgorithmException noSuchAlgorithm) {
            System.out.println(noSuchAlgorithm);
            return false;
        }
    }

    static String[] encrypt(String base64Key, String strToEncrypt)
    {
        byte[] encodedKeyBytes = Base64.decodeBase64(base64Key);
        SecretKey secretKey = new SecretKeySpec(encodedKeyBytes, "AES");

        try
        {
            // Step 1: Generate random IV
            byte[] iv = new byte[16];
            SecureRandom prng = new SecureRandom();
            prng.nextBytes(iv);
            String base64Iv = Base64.encodeBase64String(iv);

            // Step 2: Create Cipher
            // In Java, standard padding is called PKCS5Padding although it actually performs PKCS #7 padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Step 3: Encrypt data
            byte[] bytesToEncrypt = strToEncrypt.getBytes();
            byte[] encryptedBytes = cipher.doFinal(bytesToEncrypt);
            String encryptedStr = Base64.encodeBase64String(encryptedBytes);

            // Return IV and text in an array
            String[] returnVals = {base64Iv, encryptedStr};
            return returnVals;

        }
        catch (NoSuchAlgorithmException noSuchAlgorithm) {
            System.out.println("No Such Algorithm exists: " + noSuchAlgorithm);
            return null;
        }
        catch (NoSuchPaddingException noSuchPadding) {
            System.out.println("No Such Padding exists: " + noSuchPadding);
            return null;
        }
        catch (InvalidKeyException invalidKey) {
            System.out.println("Invalid Key: " + invalidKey);
            return null;
        }
        catch (InvalidAlgorithmParameterException invalidAlgorithm) {
            System.out.println("Invalid Parameter: " + invalidAlgorithm);
            return null;
        }
        catch (IllegalBlockSizeException illegalBlockSize) {
            System.out.println("Illegal Block Size: " + illegalBlockSize);
            return null;
        }
        catch (BadPaddingException badPadding) {
            System.out.println("Bad Padding: " + badPadding);
            return null;
        }
    }

    static String decrypt(String base64Key, String base64Iv, String encryptedText)
    {
        byte[] encodedKeyBytes = Base64.decodeBase64(base64Key);
        SecretKey secretKey = new SecretKeySpec(encodedKeyBytes, "AES");

        try
        {
            // Converts parameters into byte arrays
            byte[] iv = Base64.decodeBase64(base64Iv);
            byte[] encryptedBytes = Base64.decodeBase64(encryptedText);

            // Creates the cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            // Decrypts text
            byte[] decryptedText = cipher.doFinal(encryptedBytes);

            return new String(decryptedText);

        }
        catch (NoSuchAlgorithmException noSuchAlgorithm) {
            System.out.println("No Such Algorithm exists " + noSuchAlgorithm);
            return null;
        }
        catch (NoSuchPaddingException noSuchPadding) {
            System.out.println("No Such Padding exists " + noSuchPadding);
            return null;
        }
        catch (InvalidKeyException invalidKey) {
            System.out.println("Invalid Key " + invalidKey);
            return null;
        }
        catch (InvalidAlgorithmParameterException invalidAlgorithm) {
            System.out.println("Invalid Parameter " + invalidAlgorithm);
            return null;
        }
        catch (IllegalBlockSizeException illegalBlockSize) {
            System.out.println("Illegal Block Size " + illegalBlockSize);
            return null;
        }
        catch (BadPaddingException badPadding) {
            System.out.println("Bad Padding " + badPadding);
            return null;
        }
    }
}
