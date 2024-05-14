package com.uep.wap.service;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.mapper.OrderMapper;
import com.uep.wap.model.*;
import com.uep.wap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private LogisticsRepository logisticsRepository;

    @Autowired
    private ReturnRepository returnRepository;

    public void addOrder(OrderDTO orderDTO) {
        Client client = clientRepository.findById(orderDTO.getClientID()).orElseThrow(() -> new RuntimeException("Client not found"));
        List<Product> products = StreamSupport.stream(productRepository.findAllById(orderDTO.getProductIDs()).spliterator(), false).collect(Collectors.toList());
        Payment payment = null;
        if (orderDTO.getPaymentID() != null) {
            payment = paymentRepository.findById(orderDTO.getPaymentID()).orElseThrow(() -> new RuntimeException("Payment not found"));
        }
        Logistics shipment = null;
        if (orderDTO.getShipmentID() != null) {
            shipment = logisticsRepository.findById(orderDTO.getShipmentID()).orElseThrow(() -> new RuntimeException("Shipment not found"));
        }
        Return orderReturn = null;
        if (orderDTO.getOrderReturnID() != null) {
            orderReturn = returnRepository.findById(orderDTO.getOrderReturnID()).orElseThrow(() -> new RuntimeException("Return not found"));
        }
        Order order = OrderMapper.toEntity(orderDTO, client, products, payment, shipment, orderReturn);
        orderRepository.save(order);
    }

    public Iterable<OrderDTO> getAllOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderDTO getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDTO(order);
    }

    public void updateOrder(long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

        Client client = null;
        if (orderDTO.getClientID() != null) {
            client = clientRepository.findById(orderDTO.getClientID()).orElseThrow(() -> new RuntimeException("Client not found"));
        }
        List<Product> products = null;
        if (orderDTO.getProductIDs() != null) {
            products = StreamSupport.stream(productRepository.findAllById(orderDTO.getProductIDs()).spliterator(), false).collect(Collectors.toList());
        }
        Payment payment = null;
        if (orderDTO.getPaymentID() != null) {
            payment = paymentRepository.findById(orderDTO.getPaymentID()).orElseThrow(() -> new RuntimeException("Payment not found"));
        }
        Logistics shipment = null;
        if (orderDTO.getShipmentID() != null) {
            shipment = logisticsRepository.findById(orderDTO.getShipmentID()).orElseThrow(() -> new RuntimeException("Shipment not found"));
        }
        Return orderReturn = null;
        if (orderDTO.getOrderReturnID() != null) {
            orderReturn = returnRepository.findById(orderDTO.getOrderReturnID()).orElseThrow(() -> new RuntimeException("Return not found"));
        }

        Order updatedOrder = OrderMapper.toEntity(orderDTO, client, products, payment, shipment, orderReturn);
        updatedOrder.setOrderID(existingOrder.getOrderID());
        orderRepository.save(updatedOrder);
    }

    public void deleteOrder(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }
}
