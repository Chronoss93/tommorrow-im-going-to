package com.tgt.paypal;

import com.paypal.api.payments.Payment;
import com.tgt.exception.ProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paypal")
@AllArgsConstructor
public class PaypalController {

    private PayPalClient payPalClient;

    @GetMapping("/create")
    public String createPayment(@RequestParam String paymentAmount) throws ProcessingException {
        Payment payment = payPalClient.createPayment(paymentAmount);
        return payPalClient.executePaypalCall(payment);
    }

    @PostMapping("/complete")
    public Payment completePayment(@RequestBody CompletePaymentDto dto) throws ProcessingException {
        return payPalClient.completePayment(dto.getPaymentId(), dto.getPayerId());
    }


    @GetMapping("/success")
    public String successUrl() {
        return "sucess";
    }

    @GetMapping("/cancel")
    public String cancelUrl() {
        return "cancel";
    }


}
