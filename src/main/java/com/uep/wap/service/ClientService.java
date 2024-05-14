package com.uep.wap.service;

import com.uep.wap.dto.ClientDTO;
import com.uep.wap.mapper.ClientMapper;
import com.uep.wap.model.Client;
import com.uep.wap.model.CustomerHistory;
import com.uep.wap.model.Invoice;
import com.uep.wap.model.Order;
import com.uep.wap.repository.ClientRepository;
import com.uep.wap.repository.CustomerHistoryRepository;
import com.uep.wap.repository.InvoiceRepository;
import com.uep.wap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerHistoryRepository customerHistoryRepository;

    public void addClient(ClientDTO dto) {
        List<Order> orders = StreamSupport.stream(orderRepository.findAllById(dto.getOrderIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Invoice> invoices = StreamSupport.stream(invoiceRepository.findAllById(dto.getInvoiceIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<CustomerHistory> customerHistories = StreamSupport.stream(customerHistoryRepository.findAllById(dto.getCustomerHistoryIds()).spliterator(), false)
                .collect(Collectors.toList());
        Client client = ClientMapper.toEntity(dto, orders, invoices, customerHistories);
        clientRepository.save(client);
    }

    public List<ClientDTO> getAllClients() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .map(ClientMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return ClientMapper.toDTO(client);
    }

    public void updateClient(Long id, ClientDTO dto) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        List<Order> orders = StreamSupport.stream(orderRepository.findAllById(dto.getOrderIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Invoice> invoices = StreamSupport.stream(invoiceRepository.findAllById(dto.getInvoiceIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<CustomerHistory> customerHistories = StreamSupport.stream(customerHistoryRepository.findAllById(dto.getCustomerHistoryIds()).spliterator(), false)
                .collect(Collectors.toList());

        Client updatedClient = ClientMapper.toEntity(dto, orders, invoices, customerHistories);
        updatedClient.setClientID(existingClient.getClientID());
        clientRepository.save(updatedClient);
    }

    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        clientRepository.delete(client);
    }
}
