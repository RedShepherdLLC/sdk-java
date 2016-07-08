package Requests;

/**
 * Created by redshepherd on 7/7/2016.
 */
public class RedPayResponse {
    private String transferStatus;
    private String responseCode;
    private String transactionId;
    private String authCode;
    private String cardLevel;
    private String cardBrand;
    private String cardType;
    private String processorCode;
    private String app;
    private String account;
    private String cardHolderName;
    private long amount;
    private String timeStamp;
    private String text;
    private String clientIP;
    private String sequenceNumber;
    private String avsCode;

    public RedPayResponse(String mTransferStatus, String mResponseCode, String mTransactionId, String mAuthCode,
                          String mCardLevel, String mCardBrand, String mCardType, String mProcessorCode, String mApp,
                          String mAccount, String mCardHolderName, long mAmount, String mTimeStamp, String mText,
                          String mClientIP, String mSequenceNumber, String mAvsCode)
    {
        transferStatus = mTransferStatus;
        responseCode = mResponseCode;
        transactionId = mTransactionId;
        authCode = mAuthCode;
        cardLevel = mCardLevel;
        cardBrand = mCardBrand;
        cardType = mCardType;
        processorCode = mProcessorCode;
        app = mApp;
        account = mAccount;
        cardHolderName = mCardHolderName;
        amount = mAmount;
        timeStamp = mTimeStamp;
        text = mText;
        clientIP = mClientIP;
        sequenceNumber = mSequenceNumber;
        avsCode = mAvsCode;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAuthCode() {
        return authCode;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public String getCardBrand() {
        return cardBrand;
    }

    public String getCardType() {
        return cardType;
    }

    public String getProcessorCode() {
        return processorCode;
    }

    public String getApp() {
        return app;
    }

    public String getAccount() {
        return account;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public long getAmount() {
        return amount;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getText() {
        return text;
    }

    public String getClientIP() {
        return clientIP;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public String getAvsCode() {
        return avsCode;
    }
}
