package com.uep.wap.service;

import com.uep.wap.dto.InvoiceDTO;
import com.uep.wap.mapper.InvoiceMapper;
import com.uep.wap.model.Client;
import com.uep.wap.model.Invoice;
import com.uep.wap.model.Order;
import com.uep.wap.repository.ClientRepository;
import com.uep.wap.repository.InvoiceRepository;
import com.uep.wap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    public void addInvoice(InvoiceDTO invoiceDTO) {
        Order order = orderRepository.findById(invoiceDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Client client = clientRepository.findById(invoiceDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        Invoice invoice = InvoiceMapper.toEntity(invoiceDTO, order, client);
        invoiceRepository.save(invoice);
    }

    public List<InvoiceDTO> getAllInvoices() {
        return StreamSupport.stream(invoiceRepository.findAll().spliterator(), false)
                .map(InvoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public InvoiceDTO getInvoiceById(long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        return InvoiceMapper.toDTO(invoice);
    }

    public void updateInvoice(long id, InvoiceDTO invoiceDTO) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        Order order = orderRepository.findById(invoiceDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Client client = clientRepository.findById(invoiceDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Invoice updatedInvoice = InvoiceMapper.toEntity(invoiceDTO, order, client);
        updatedInvoice.setInvoiceId(existingInvoice.getInvoiceId());
        invoiceRepository.save(updatedInvoice);
    }

    public void deleteInvoice(long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoiceRepository.delete(invoice);
    }
}
