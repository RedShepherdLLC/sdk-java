package Requests;

import com.google.gson.Gson;
import java.io.IOException;

/**
 * Created by redshepherd on 7/6/2016.
 */
public class ChargeBuilder
{
    private Config config;

    private static String transactionId = null;
    private static String account = null;
    private static long amount;
    private static String expmmyyyy = null;
    private static String cvv = null;
    private static String track1Data = null;
    private static String track2Data = null;
    private static String signatureData = null;
    private static String cardHolderName = null;
    private static String method = null;
    private static String currency = "USD";
    private static String authCode = null;
    private static String retryCount = null;
    private static String avsAddress1 = null;
    private static String avsAddress2 = null;
    private static String avsCity = null;
    private static String avsZip = null;
    private static String cardHolderEmail = null;
    private static String cardHolderPhone = null;
    private static String employeeRefNum = null;
    private static String customerRefNum = null;
    private static String orderRefNum = null;
    private static String terminalRefNum = null;

    public ChargeBuilder(Config config){
        this.config = config;
    }

    public RedPayResponse create() throws IOException
    {
        // Creates a RedPayRequest object
        RedPayRequest charge = new RedPayRequest("A", transactionId, account, amount, expmmyyyy, cvv, track1Data,
                track2Data, signatureData, cardHolderName, method, currency, authCode, retryCount,
                avsAddress1, avsAddress2, avsCity, avsZip, cardHolderEmail, cardHolderPhone, employeeRefNum,
                customerRefNum, orderRefNum, terminalRefNum);

        // Serializes RedPayRequest
        Gson gson = new Gson();
        String data = gson.toJson(charge);

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
            System.out.println("Encryption Failed");
            return null;
        }

        // Creates the packet
        Packet payload = new Packet(config.getApp(), aesData, iv);

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

    public ChargeBuilder transactionId(String mTransactionId)
    {
        transactionId = mTransactionId;
        return this;
    }

    public ChargeBuilder track1Data(String mTrack1Data)
    {
        track1Data = mTrack1Data;
        return this;
    }

    public ChargeBuilder track2Data(String mTrack2Data)
    {
        track2Data = mTrack2Data;
        return this;
    }

    public ChargeBuilder signatureData(String mSignatureData)
    {
        signatureData = mSignatureData;
        return this;
    }

    public ChargeBuilder account(String acct)
    {
        account = acct;
        return this;
    }

    public ChargeBuilder amount(long amt)
    {
        amount = amt;
        return this;
    }

    public ChargeBuilder avsZip(String zip)
    {
        avsZip = zip;
        return this;
    }

    public ChargeBuilder expDate(String exp)
    {
        expmmyyyy = exp;
        return this;
    }

    public ChargeBuilder currency(String cur)
    {
        currency = cur;
        return this;
    }

    public ChargeBuilder cvv(String mCvv)
    {
        cvv = mCvv;
        return this;
    }

    public ChargeBuilder cardHolderName(String mCardHolderName)
    {
        cardHolderName = mCardHolderName;
        return this;
    }

    public ChargeBuilder method(String mMethod)
    {
        method = mMethod;
        return this;
    }

    public ChargeBuilder authCode(String mAuthCode)
    {
        authCode = mAuthCode;
        return this;
    }

    public ChargeBuilder retryCount(String mRetryCount)
    {
        retryCount = mRetryCount;
        return this;
    }

    public ChargeBuilder avsAddress1(String mAvsAddress1)
    {
        avsAddress1 = mAvsAddress1;
        return this;
    }

    public ChargeBuilder avsAddress2(String mAvsAddress2)
    {
        avsAddress2 = mAvsAddress2;
        return this;
    }

    public ChargeBuilder avsCity(String mAvsCity)
    {
        avsCity = mAvsCity;
        return this;
    }

    public ChargeBuilder cardHolderEmail(String mCardHolderEmail)
    {
        cardHolderEmail = mCardHolderEmail;
        return this;
    }

    public ChargeBuilder cardHolderPhone(String mCardHolderPhone)
    {
        cardHolderPhone = mCardHolderPhone;
        return this;
    }

    public ChargeBuilder employeeRefNum(String mEmployeeRefNum)
    {
        employeeRefNum = mEmployeeRefNum;
        return this;
    }

    public ChargeBuilder customerRefNum(String mCustomerRefNum)
    {
        customerRefNum = mCustomerRefNum;
        return this;
    }

    public ChargeBuilder orderRefNum(String mOrderRefNum)
    {
        orderRefNum = mOrderRefNum;
        return this;
    }

    public ChargeBuilder terminalRefNum(String mTerminalRefNum)
    {
        terminalRefNum = mTerminalRefNum;
        return this;
    }
}
