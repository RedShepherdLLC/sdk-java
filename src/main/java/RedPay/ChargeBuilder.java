package RedPay;

import java.io.IOException;
import java.security.InvalidParameterException;

public class ChargeBuilder
{
    private Config config;

    private String transactionId = null;
    private String account = null;
    private long amount;
    private String expmmyyyy = null;
    private String cvv = null;
    private String track1Data = null;
    private String track2Data = null;
    private String signatureData = null;
    private String cardHolderName = null;
    private String method = null;
    private String currency = "USD";
    private String authCode = null;
    private String retryCount = null;
    private String avsAddress1 = null;
    private String avsAddress2 = null;
    private String avsCity = null;
    private String avsZip = null;
    private String cardHolderEmail = null;
    private String cardHolderPhone = null;
    private String employeeRefNum = null;
    private String customerRefNum = null;
    private String orderRefNum = null;
    private String terminalRefNum = null;

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

        // Checks card data
        validate();

        // POSTs serialized packet to RedPay
        RedPayResponse redPayResponse = Packet.POST(config, charge);

        return redPayResponse;
    }

    // Validates card information before making a server call
    private void validate() {
        // Amount
        if (amount <= 0)
            throw new InvalidParameterException("Missing amount. Amount must be greater than 0");
        // Card number
        try {
            Long.parseLong(account);
        }
        catch (NumberFormatException ex) {
            throw new InvalidParameterException("Missing/Invalid account number. Account number must only contain digits");
        }
        // Cardholder name
        if (cardHolderName == null)
            throw new InvalidParameterException("Missing cardholder name. Please provide a string before making a charge");
        // Expiration date
        try {
            Long.parseLong(expmmyyyy);
        }
        catch (NumberFormatException ex) {
            throw new InvalidParameterException("Missing/Invalid expiration date. Expiration date can only contain 6 digits");
        }
        // Cvv
        try {
            Long.parseLong(cvv);
        }
        catch (NumberFormatException ex) {
            throw new InvalidParameterException("Missing/Invalid card cvv. Card cvv must only contain digits.");
        }
        try {
            Long.parseLong(avsZip);
        }
        catch (NumberFormatException ex) {
            throw new InvalidParameterException("Missing/Invalid zip code. Please provide a digit string");
        }

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
