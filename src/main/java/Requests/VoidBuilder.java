package Requests;

import com.google.gson.Gson;
import java.io.IOException;

/**
 * Created by redshepherd on 7/6/2016.
 */
public class VoidBuilder
{
    private Config config;
    private static String transactionId = null;

    public VoidBuilder(Config config){
        this.config = config;
    }

    public RedPayResponse create() throws IOException
    {
        // Creates a RedPayRequest object
        RedPayRequest _void = new RedPayRequest("V", transactionId, null, 0, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        // Serializes RedPayRequest
        Gson gson = new Gson();
        String data = gson.toJson(_void);

        /// Encrypts RedPayRequest
        String[] enc = Crypto.encrypt(config.getBase64Key(), data);
        String iv, aesData;
        if (enc != null && enc.length == 2)
        {
            iv = enc[0];
            aesData = enc[1];
        }
        else
        {
            System.out.println("Encryption Failed");
            return null;
        }

        // Creates the packet
        Packet payload = new Packet("DEMO", aesData, iv);

        // Serializes Packet
        String json = gson.toJson(payload);

        // POSTs serialized packet to RedPay
        String response = Packet.POST(config.getUrl(), json);
        Packet responsePacket = gson.fromJson(response, Packet.class);

        // Decrypts RedPayResponse
        String encryptedResponseIv = responsePacket.getIv();
        String encryptedResponseData = responsePacket.getAesData();
        String decryptedData = Crypto.decrypt(config.getBase64Key(), encryptedResponseIv, encryptedResponseData);
        RedPayResponse redPayResponse = gson.fromJson(decryptedData, RedPayResponse.class);

        System.out.println("\nResponse Data: \n" + decryptedData);

        return redPayResponse;
    }

    public VoidBuilder transactionId(String mTransactionId)
    {
        transactionId = mTransactionId;
        return this;
    }
}
