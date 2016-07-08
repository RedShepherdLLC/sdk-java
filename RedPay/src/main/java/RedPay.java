/**
 * Created by redshepherd on 7/6/2016.
 */
import java.io.IOException;
import java.util.Random;

import Requests.*;

public class RedPay {

    public static void main(String[] args) throws IOException {

        // Validates client's JDK encryption key size
        if (!Crypto.validateClientJDK()){
            return;
        }

        // Used to randomize the amounts charged
        Random random = new Random();
        int max = 10000;
        int min = 1000;

        // Sets up client's app name, encryption key, and endpoint
        Config config = new Config("DEMO", "vZ9cvj3lONTEGWmuzTJ9tdjmDoEUEb7dPkdMdXyP1/4=","https://redpaydemo.azurewebsites.net/card");

        // Charges a credit card
        ChargeBuilder chargeBuilder = new ChargeBuilder(config)
                .account("4111111111111111")
                .amount(random.nextInt(max - min + 1) + min)
                .avsZip("60603")
                .expDate("072018");
        RedPayResponse chargeResponse = chargeBuilder.create();
        System.out.println("Charge Status: " + chargeResponse.getText());

        // Voids a credit card charge
        VoidBuilder voidBuilder = new VoidBuilder(config)
                .transactionId(chargeResponse.getTransactionId());
        RedPayResponse voidResponse = voidBuilder.create();
        System.out.println("Void Status: " + voidResponse.getText());

        // Charges a credit card
        chargeBuilder = new ChargeBuilder(config)
                .account("4111111111111111")
                .amount(random.nextInt(max - min + 1) + min)
                .avsZip("60603")
                .expDate("072018");
        chargeResponse = chargeBuilder.create();
        System.out.println("Charge Status: " + chargeResponse.getText());

        // Refunds a credit card charge
        RefundBuilder refundBuilder = new RefundBuilder(config)
                .transactionId(chargeResponse.getTransactionId())
                .amount(chargeResponse.getAmount());
        RedPayResponse refundResponse = refundBuilder.create();
        System.out.println("Refund Status: " + refundResponse.getText());
    }
}
