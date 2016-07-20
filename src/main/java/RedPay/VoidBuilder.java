package RedPay;

import java.io.IOException;
import java.security.InvalidParameterException;

public class VoidBuilder
{
    private Config config;
    private String transactionId = null;

    public VoidBuilder(Config config){
        this.config = config;
    }

    public RedPayResponse create() throws IOException
    {
        // Creates a RedPayRequest object
        RedPayRequest voidCharge = new RedPayRequest("V", transactionId, null, 0, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        validate();

        // POSTs serialized packet to RedPay
        RedPayResponse redPayResponse = Packet.POST(config, voidCharge);

        return redPayResponse;
    }

    private void validate() {
        if (transactionId == null)
            throw new InvalidParameterException("Missing transaction id");
    }

    public VoidBuilder transactionId(String mTransactionId)
    {
        transactionId = mTransactionId;
        return this;
    }
}
