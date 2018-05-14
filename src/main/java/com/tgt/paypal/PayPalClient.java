package com.tgt.paypal;

import com.paypal.api.payments.Payment;
import com.tgt.exception.ProcessingException;

public interface PayPalClient {

    Payment createPayment(String paymentAmount);

    String executePaypalCall(Payment payment) throws ProcessingException;

    CompletedPaymentDto completePayment(String paymentId, String payerId) throws ProcessingException;
}
