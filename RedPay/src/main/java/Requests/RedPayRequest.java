package Requests;

/**
 * Created by redshepherd on 7/6/2016.
 */
class RedPayRequest {
    private String action;
    private String transactionId;
    private String account;
    private long amount;
    private String expmmyyyy;
    private String cvv;
    private String track1Data;
    private String track2Data;
    private String signatureData;
    private String cardHolderName;
    private String method;
    private String currency;
    private String authCode;
    private String retryCount;
    private String avsAddress1;
    private String avsAddress2;
    private String avsCity;
    private String avsZip;
    private String cardHolderEmail;
    private String cardHolderPhone;
    private String employeeRefNum;
    private String customerRefNum;
    private String orderRefNum;
    private String terminalRefNum;

    RedPayRequest(String mAction, String mTransactionId, String mAccount, long mAmount, String mExpmmyyyy,
                         String mCvv, String mTrack1Data, String mTrack2Data, String mSignatureData,
                         String mCardHolderName, String mMethod, String mCurrency, String mAuthCode,
                         String mRetryCount, String mAvsAddress1, String mAvsAddress2, String mAvsCity, String mAvsZip,
                         String mCardHolderEmail, String mCardHolderPhone, String mEmployeeRefNum, String mCustomerRefNum,
                         String mOrderRefNum, String mTerminalRefNum)
    {
        action = mAction;
        transactionId = mTransactionId;
        account = mAccount;
        track1Data = mTrack1Data;
        track2Data = mTrack2Data;
        signatureData = mSignatureData;
        expmmyyyy = mExpmmyyyy;
        cvv = mCvv;
        amount = mAmount;
        cardHolderName = mCardHolderName;
        method = mMethod;
        currency = mCurrency;
        authCode = mAuthCode;
        retryCount = mRetryCount;
        avsAddress1 = mAvsAddress1;
        avsAddress2 = mAvsAddress2;
        avsCity = mAvsCity;
        avsZip = mAvsZip;
        cardHolderEmail = mCardHolderEmail;
        cardHolderPhone = mCardHolderPhone;
        employeeRefNum = mEmployeeRefNum;
        customerRefNum = mCustomerRefNum;
        orderRefNum = mOrderRefNum;
        terminalRefNum = mTerminalRefNum;
    }
}
