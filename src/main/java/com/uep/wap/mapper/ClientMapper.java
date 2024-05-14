package com.uep.wap.mapper;

import com.uep.wap.dto.ClientDTO;
import com.uep.wap.model.Client;
import com.uep.wap.model.CustomerHistory;
import com.uep.wap.model.Invoice;
import com.uep.wap.model.Order;

import java.util.List;
import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDTO toDTO(Client client) {
        if (client == null) {
            return null;
        }

        ClientDTO dto = new ClientDTO();
        dto.setClientID(client.getClientID());
        dto.setOrderIds(client.getOrders().stream().map(Order::getOrderID).collect(Collectors.toList()));
        dto.setInvoiceIds(client.getInvoices().stream().map(Invoice::getInvoiceId).collect(Collectors.toList()));
        dto.setCustomerHistoryIds(client.getCustomerHistories().stream().map(CustomerHistory::getCustomerHistoryID).collect(Collectors.toList()));
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setAddress(client.getAddress());

        return dto;
    }

    public static Client toEntity(ClientDTO dto, List<Order> orders, List<Invoice> invoices, List<CustomerHistory> customerHistories) {
        if (dto == null) {
            return null;
        }

        Client client = new Client();
        client.setClientID(dto.getClientID());
        client.setOrders(orders);
        client.setInvoices(invoices);
        client.setCustomerHistories(customerHistories);
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setAddress(dto.getAddress());

        return client;
    }
}
