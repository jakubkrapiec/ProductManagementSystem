package com.uep.wap.mapper;

import com.uep.wap.dto.PaymentDTO;
import com.uep.wap.model.Order;
import com.uep.wap.model.Payment;

public class PaymentMapper {

    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setOrderId(payment.getOrder() != null ? payment.getOrder().getOrderID() : null);
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());

        return paymentDTO;
    }

    public static Payment toEntity(PaymentDTO paymentDTO, Order order) {
        if (paymentDTO == null) {
            return null;
        }

        Payment payment = new Payment();
        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setOrder(order);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        return payment;
    }
}
