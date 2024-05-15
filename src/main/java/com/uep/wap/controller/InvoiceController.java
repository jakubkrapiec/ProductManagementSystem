package com.uep.wap.controller;

import com.uep.wap.dto.InvoiceDTO;
import com.uep.wap.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceDTO> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.ok(invoiceService.addInvoice(invoiceDTO));
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices() {
        List<InvoiceDTO> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDTO> getInvoiceById(@PathVariable Long id) {
        InvoiceDTO invoiceDTO = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoiceDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, invoiceDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
