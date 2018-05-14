package com.tgt.paypal;

import com.paypal.api.payments.Payee;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CompletedPaymentDto {

    private String id;
    private Payee payee;
    private Payer payer;
    private String state;

    public static CompletedPaymentDto of(Payment completedPayment) {
        CompletedPaymentDto dto = new CompletedPaymentDto();

        dto.setId(completedPayment.getId());
        dto.setPayee(completedPayment.getPayee());
        dto.setPayer(completedPayment.getPayer());
        dto.setState(completedPayment.getState());
        return dto;
    }
}
