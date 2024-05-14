package com.uep.wap.mapper;

import com.uep.wap.dto.OrderDTO;
import com.uep.wap.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(order.getOrderID());
        orderDTO.setClientID(order.getClient() != null ? order.getClient().getClientID() : null);
        orderDTO.setProductIDs(order.getProducts() != null ?
                order.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()) : null);
        orderDTO.setPaymentID(order.getPayment() != null ? order.getPayment().getPaymentId() : null);
        orderDTO.setShipmentID(order.getShipment() != null ? order.getShipment().getShipmentId() : null);
        orderDTO.setOrderReturnID(order.getOrderReturn() != null ? order.getOrderReturn().getReturnId() : null);
        orderDTO.setOrderNumber(order.getOrderNumber());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus().name());

        return orderDTO;
    }

    public static Order toEntity(OrderDTO orderDTO, Client client, List<Product> products, Payment payment, Logistics shipment, Return orderReturn) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderID(orderDTO.getOrderID());
        order.setClient(client);
        order.setProducts(products);
        order.setPayment(payment);
        order.setShipment(shipment);
        order.setOrderReturn(orderReturn);
        order.setOrderNumber(orderDTO.getOrderNumber());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(Order.OrderStatus.valueOf(orderDTO.getStatus()));

        return order;
    }
}
