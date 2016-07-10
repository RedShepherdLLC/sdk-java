package Requests;

import okhttp3.*;
import java.io.IOException;

/**
 * Created by redshepherd on 7/6/2016.
 */
class Packet
{
    private static OkHttpClient client = new OkHttpClient();

    private String app;
    private String aesData;
    private String iv;

    Packet(String mApp, String mAesData, String mIv)
    {
        app = mApp;
        aesData = mAesData;
        iv = mIv;
    }

    String getAesData()
    {
        return aesData;
    }

    String getIv()
    {
        return iv;
    }

    // POSTs the json information to the demo URL
    static String POST(String url, String jsonData) throws IOException {

        // Specifies type of data in the body
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, jsonData);

        // Builds the request with the json info
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();

        // Gets the response from RedPay
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
