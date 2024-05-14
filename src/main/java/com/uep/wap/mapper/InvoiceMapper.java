package com.uep.wap.mapper;

import com.uep.wap.dto.InvoiceDTO;
import com.uep.wap.model.Client;
import com.uep.wap.model.Invoice;
import com.uep.wap.model.Order;

public class InvoiceMapper {

    public static InvoiceDTO toDTO(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceId(invoice.getInvoiceId());
        invoiceDTO.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDTO.setOrderId(invoice.getOrder() != null ? invoice.getOrder().getOrderID() : null);
        invoiceDTO.setClientId(invoice.getClient() != null ? invoice.getClient().getClientID() : null);
        invoiceDTO.setDate(invoice.getDate());
        invoiceDTO.setPrice(invoice.getPrice());

        return invoiceDTO;
    }

    public static Invoice toEntity(InvoiceDTO invoiceDTO, Order order, Client client) {
        if (invoiceDTO == null) {
            return null;
        }

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceDTO.getInvoiceId());
        invoice.setInvoiceNumber(invoiceDTO.getInvoiceNumber());
        invoice.setOrder(order);
        invoice.setClient(client);
        invoice.setDate(invoiceDTO.getDate());
        invoice.setPrice(invoiceDTO.getPrice());

        return invoice;
    }
}
