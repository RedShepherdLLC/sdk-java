package RedPay;

import okhttp3.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.concurrent.RejectedExecutionException;

class Packet
{
    private static OkHttpClient client = new OkHttpClient();

    private String app;
    private String aesData;
    private String iv;

    private Packet(String mApp, String mAesData, String mIv)
    {
        app = mApp;
        aesData = mAesData;
        iv = mIv;
    }

    private String getAesData()
    {
        return aesData;
    }

    private String getIv()
    {
        return iv;
    }

    // POSTs the json information to the demo URL
    static RedPayResponse POST(Config config, RedPayRequest req) throws IOException {

        // Serializes RedPayRequest
        Gson gson = new Gson();
        String data = gson.toJson(req);

        // Encrypts RedPayRequest
        String[] enc = Crypto.encrypt(config.getBase64Key(), data);
        String iv, aesData;
        if (enc != null && enc.length == 2)
        {
            iv = enc[0];
            aesData = enc[1];
        }
        else
        {
            throw new IllegalStateException("Encryption Failed");
        }

        // Creates and serializes packet
        Packet payload = new Packet(config.getApp(), aesData, iv);
        String jsonPayload = gson.toJson(payload);

        // Specifies type of data in the body
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, jsonPayload);

        // Builds the request with the json info
        Request request = new Request.Builder()
                .url(config.getUrl())
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        // Gets the response from RedPay
        Response response = client.newCall(request).execute();
        Packet responsePacket;

        // Throws exception if engine is down
        responsePacket = gson.fromJson(response.body().string(), Packet.class);


        // Decrypts RedPayResponse
        String encryptedResponseIv = responsePacket.getIv();
        String encryptedResponseData = responsePacket.getAesData();
        String decryptedData = Crypto.decrypt(config.getBase64Key(), encryptedResponseIv, encryptedResponseData);
        RedPayResponse redPayResponse = gson.fromJson(decryptedData, RedPayResponse.class);

        // System.out.println("\nResponse Data: \n" + decryptedData);

        return redPayResponse;
    }
}
