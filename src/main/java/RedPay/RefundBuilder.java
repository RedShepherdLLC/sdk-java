package RedPay;

import java.io.IOException;
import java.security.InvalidParameterException;

public class RefundBuilder
{
    private Config config;

    private String transactionId = null;
    private long amount;

    public RefundBuilder(Config config){
        this.config = config;
    }

    public RedPayResponse create() throws IOException
    {
        // Creates a RedPayRequest object
        RedPayRequest refundCharge = new RedPayRequest("R", transactionId, null, amount, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        validate();

        // POSTs serialized packet to RedPay
        RedPayResponse redPayResponse = Packet.POST(config, refundCharge);

        // System.out.println("\nResponse Data: \n" + decryptedData);

        return redPayResponse;
    }

    private void validate() {
        if (amount <= 0)
            throw new InvalidParameterException("Missing amount. Amount must be greater than 0");
        if (transactionId == null)
            throw new InvalidParameterException("Missing transaction id");
    }

    public RefundBuilder transactionId(String mTransactionId)
    {
        transactionId = mTransactionId;
        return this;
    }

    public RefundBuilder amount(long mAmount)
    {
        amount = mAmount;
        return this;
    }
}
