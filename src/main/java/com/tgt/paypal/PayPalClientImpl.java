package com.tgt.paypal;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.tgt.exception.ProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PayPalClientImpl implements PayPalClient {

    /**
     * Paypal mode: `live` or `sandbox`.
     */
    public static final String SANDBOX_MODE = "sandbox";
    /**
     * Client configs for registered application
     */
    @Value("${paypal.client.id}")
    private String clientId;
    @Value("${paypal.client.secret}")
    private String clientSecret;


    @Override
    public Payment createPayment(String paymentAmount) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(paymentAmount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("This is the payment transaction description");
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/api/v1/paypal/cancel");
        redirectUrls.setReturnUrl("http://localhost:8080/api/v1/paypal/success");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        payment.setRedirectUrls(redirectUrls);
        return payment;
    }

    @Override
    public String executePaypalCall(Payment payment) throws ProcessingException {
        try {
            APIContext apiContext = new APIContext(clientId, clientSecret, SANDBOX_MODE);
            Payment createdPayment = payment.create(apiContext);
            log.debug("Created PayPal payment:\n" + createdPayment);

            for (Links link : createdPayment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return link.getHref();
                }
            }
            throw new ProcessingException("Payment is invalid, approval_url does not exist");
        } catch (PayPalRESTException ex) {
            throw new ProcessingException("Couldn't create payment. PayPal processing exception", ex);
        }
    }

    @Override
    public Payment completePayment(String paymentId, String payerId) throws ProcessingException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        try {
            APIContext context = new APIContext(clientId, clientSecret, SANDBOX_MODE);
            Payment completedPayment = payment.execute(context, paymentExecution);
            log.debug("Completed PayPal payment:\n" + completedPayment);
            return completedPayment;
        } catch (PayPalRESTException e) {
            throw new ProcessingException("Couldn't create payment. PayPal processing exception", e);
        }
    }

}
