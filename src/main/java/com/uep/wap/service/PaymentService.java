package com.uep.wap.service;

import com.uep.wap.dto.PaymentDTO;
import com.uep.wap.mapper.PaymentMapper;
import com.uep.wap.model.Order;
import com.uep.wap.model.Payment;
import com.uep.wap.repository.OrderRepository;
import com.uep.wap.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void addPayment(PaymentDTO paymentDTO) {
        Order order = orderRepository.findById(paymentDTO.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Payment payment = PaymentMapper.toEntity(paymentDTO, order);
        paymentRepository.save(payment);
    }

    public Iterable<PaymentDTO> getAllPayments() {
        return StreamSupport.stream(paymentRepository.findAll().spliterator(), false)
                .map(PaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        return PaymentMapper.toDTO(payment);
    }

    public void updatePayment(Long id, PaymentDTO paymentDTO) {
        Payment existingPayment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        Order order = orderRepository.findById(paymentDTO.getOrderId()).orElseThrow(() -> new RuntimeException("Order not found"));
        Payment updatedPayment = PaymentMapper.toEntity(paymentDTO, order);
        updatedPayment.setPaymentId(existingPayment.getPaymentId());
        paymentRepository.save(updatedPayment);
    }

    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
        paymentRepository.delete(payment);
    }
}
