package RedPay;

import org.apache.commons.codec.binary.*;

import java.security.*;

import javax.crypto.SecretKey;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class Crypto {

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
            throw new IllegalStateException("No Such Algorithm exists: " + noSuchAlgorithm);
        }
        catch (NoSuchPaddingException noSuchPadding) {
            throw new IllegalStateException("No Such Padding exists: " + noSuchPadding);
        }
        catch (InvalidKeyException invalidKey) {
            throw new IllegalStateException("Invalid Key: " + invalidKey);
        }
        catch (InvalidAlgorithmParameterException invalidAlgorithm) {
            throw new IllegalStateException("Invalid Parameter: " + invalidAlgorithm);
        }
        catch (IllegalBlockSizeException illegalBlockSize) {
            throw new IllegalStateException("Illegal Block Size: " + illegalBlockSize);
        }
        catch (BadPaddingException badPadding) {
            throw new IllegalStateException("Bad Padding: " + badPadding);
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
            throw new IllegalStateException("No Such Algorithm exists " + noSuchAlgorithm);
        }
        catch (NoSuchPaddingException noSuchPadding) {
            throw new IllegalStateException("No Such Padding exists " + noSuchPadding);
        }
        catch (InvalidKeyException invalidKey) {
            throw new IllegalStateException("Invalid Key " + invalidKey);
        }
        catch (InvalidAlgorithmParameterException invalidAlgorithm) {
            throw new IllegalStateException("Invalid Parameter " + invalidAlgorithm);
        }
        catch (IllegalBlockSizeException illegalBlockSize) {
            throw new IllegalStateException("Illegal Block Size " + illegalBlockSize);
        }
        catch (BadPaddingException badPadding) {
            throw new IllegalStateException("Bad Padding " + badPadding);
        }
    }
}
