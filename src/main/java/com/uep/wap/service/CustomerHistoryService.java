package com.uep.wap.service;

import com.uep.wap.dto.CustomerHistoryDTO;
import com.uep.wap.mapper.CustomerHistoryMapper;
import com.uep.wap.model.Client;
import com.uep.wap.model.CustomerHistory;
import com.uep.wap.model.Order;
import com.uep.wap.model.Product;
import com.uep.wap.repository.ClientRepository;
import com.uep.wap.repository.CustomerHistoryRepository;
import com.uep.wap.repository.OrderRepository;
import com.uep.wap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerHistoryService {

    @Autowired
    private CustomerHistoryRepository customerHistoryRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addCustomerHistory(CustomerHistoryDTO dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        List<Order> orders = StreamSupport.stream(orderRepository.findAllById(dto.getOrderIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());
        CustomerHistory customerHistory = CustomerHistoryMapper.toEntity(dto, client, orders, products);
        customerHistoryRepository.save(customerHistory);
    }

    public List<CustomerHistoryDTO> getAllCustomerHistories() {
        return StreamSupport.stream(customerHistoryRepository.findAll().spliterator(), false)
                .map(CustomerHistoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CustomerHistoryDTO getCustomerHistoryById(Long id) {
        CustomerHistory customerHistory = customerHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerHistory not found"));
        return CustomerHistoryMapper.toDTO(customerHistory);
    }

    public void updateCustomerHistory(Long id, CustomerHistoryDTO dto) {
        CustomerHistory existingCustomerHistory = customerHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerHistory not found"));

        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        List<Order> orders = StreamSupport.stream(orderRepository.findAllById(dto.getOrderIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());

        CustomerHistory updatedCustomerHistory = CustomerHistoryMapper.toEntity(dto, client, orders, products);
        updatedCustomerHistory.setCustomerHistoryID(existingCustomerHistory.getCustomerHistoryID());
        customerHistoryRepository.save(updatedCustomerHistory);
    }

    public void deleteCustomerHistory(Long id) {
        CustomerHistory customerHistory = customerHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerHistory not found"));
        customerHistoryRepository.delete(customerHistory);
    }
}
