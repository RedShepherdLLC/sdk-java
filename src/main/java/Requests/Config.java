package Requests;

/**
 * Created by redshepherd on 7/8/2016.
 */
public class Config {
    private String app;
    private String base64Key;
    private String url;

    public Config(String mApp, String mBase64Key, String mUrl)
    {
        this.app = mApp;
        this.base64Key = mBase64Key;
        this.url = mUrl;
    }

    public String getApp() {
        return app;
    }

    public String getBase64Key() {
        return base64Key;
    }

    public String getUrl() {
        return url;
    }
}
