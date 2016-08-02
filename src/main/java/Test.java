import java.io.IOException;
import java.security.KeyManagementException;
import java.util.Random;

import RedPay.*;


public class Test {

    public static void main(String[] args) throws KeyManagementException, IOException {

        // Used to randomize the amounts charged
        Random random = new Random();
        int max = 10000;
        int min = 1000;

        // Sets up client's app name, encryption key, and endpoint
        Config config = new Config("DEMO", "vZ9cvj3lONTEGWmuzTJ9tdjmDoEUEb7dPkdMdXyP1/4=","https://redpaydemo.azurewebsites.net/card");

        // Charges a credit card
        ChargeBuilder chargeBuilder = new ChargeBuilder(config)
                .account("4111111111111111")
                .cardHolderName("John Doe")
                .amount(random.nextInt(max - min + 1) + min)
                .avsZip("60603")
                .expDate("072018")
                .cvv("123");
        RedPayResponse chargeResponse = chargeBuilder.create();
        System.out.println("Charge Status: " + chargeResponse.getResponseCode());

        // Voids a credit card charge
        VoidBuilder voidBuilder = new VoidBuilder(config)
                .transactionId(chargeResponse.getTransactionId());
        RedPayResponse voidResponse = voidBuilder.create();
        System.out.println("Void Status: " + voidResponse.getResponseCode());

        // Charges a credit card
        chargeBuilder = new ChargeBuilder(config)
                .account("4111111111111111")
                .cardHolderName("John Doe")
                .amount(random.nextInt(max - min + 1) + min)
                .avsZip("60603")
                .expDate("072018")
                .cvv("123");
        chargeResponse = chargeBuilder.create();
        System.out.println("Charge Status: " + chargeResponse.getResponseCode());

        // Refunds a credit card charge
        RefundBuilder refundBuilder = new RefundBuilder(config)
                .transactionId(chargeResponse.getTransactionId())
                .amount(chargeResponse.getAmount());
        RedPayResponse refundResponse = refundBuilder.create();
        System.out.println("Refund Status: " + refundResponse.getResponseCode());
    }
}
