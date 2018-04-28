package com.tgt.paypal;

import lombok.Data;

@Data
public class CompletePaymentDto {
    private String paymentId;
    private String payerId;
}
