package com.uep.wap.mapper;

import com.uep.wap.dto.CustomerHistoryDTO;
import com.uep.wap.model.Client;
import com.uep.wap.model.CustomerHistory;
import com.uep.wap.model.Order;
import com.uep.wap.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerHistoryMapper {

    public static CustomerHistoryDTO toDTO(CustomerHistory customerHistory) {
        if (customerHistory == null) {
            return null;
        }

        CustomerHistoryDTO dto = new CustomerHistoryDTO();
        dto.setCustomerHistoryID(customerHistory.getCustomerHistoryID());
        dto.setClientId(customerHistory.getClient().getClientID());
        dto.setOrderIds(customerHistory.getOrders().stream().map(Order::getOrderID).collect(Collectors.toList()));
        dto.setProductIds(customerHistory.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()));

        return dto;
    }

    public static CustomerHistory toEntity(CustomerHistoryDTO dto, Client client, List<Order> orders, List<Product> products) {
        if (dto == null) {
            return null;
        }

        CustomerHistory customerHistory = new CustomerHistory();
        customerHistory.setCustomerHistoryID(dto.getCustomerHistoryID());
        customerHistory.setClient(client);
        customerHistory.setOrders(orders);
        customerHistory.setProducts(products);

        return customerHistory;
    }
}
