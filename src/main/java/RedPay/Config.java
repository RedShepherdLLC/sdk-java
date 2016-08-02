package RedPay;

import javax.crypto.Cipher;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Config {
    private String app;
    private String base64Key;
    private String url;

    public Config(String mApp, String mBase64Key, String mUrl) throws KeyManagementException
    {
        // Validates client's JDK encryption key size
        try {
            int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
            if (maxKeyLen == 128)
            {
                throw new KeyManagementException("Your installed JDK restricts key sizes to 128 bits. Our software requires the use of 256 bit keys for encryption.\nTo remove this restriction, please download the JCE Unlimited Strength Jurisdiction Policy Files from http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html\nExtract the .zip file and place the .jar files in your Java\\jdk\\jre\\lib\\security folder. This will replace the pre-installed files.");
            }
        }
        catch (NoSuchAlgorithmException noSuchAlgorithm) {
            System.out.println("WARNING: Could not determine the maximum allowed key length of your JDK.\nIf the max key length of your system is restricted to 128 bits, the transaction will fail.\nProceeding...");
        }

        // Creates the object if no exceptions were thrown
        this.app = mApp;
        this.base64Key = mBase64Key;
        this.url = mUrl;
    }

    String getApp() {
        return app;
    }

    String getBase64Key() {
        return base64Key;
    }

    String getUrl() {
        return url;
    }
}
