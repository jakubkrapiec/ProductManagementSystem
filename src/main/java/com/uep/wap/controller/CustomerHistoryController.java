package com.uep.wap.controller;

import com.uep.wap.dto.CustomerHistoryDTO;
import com.uep.wap.service.CustomerHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customerHistories")
public class CustomerHistoryController {

    @Autowired
    private CustomerHistoryService customerHistoryService;

    @PostMapping
    public ResponseEntity<CustomerHistoryDTO> addCustomerHistory(@RequestBody CustomerHistoryDTO dto) {
        customerHistoryService.addCustomerHistory(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerHistoryDTO>> getAllCustomerHistories() {
        List<CustomerHistoryDTO> customerHistories = customerHistoryService.getAllCustomerHistories();
        return ResponseEntity.ok(customerHistories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerHistoryDTO> getCustomerHistoryById(@PathVariable Long id) {
        CustomerHistoryDTO dto = customerHistoryService.getCustomerHistoryById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerHistoryDTO> updateCustomerHistory(@PathVariable Long id, @RequestBody CustomerHistoryDTO dto) {
        customerHistoryService.updateCustomerHistory(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerHistory(@PathVariable Long id) {
        customerHistoryService.deleteCustomerHistory(id);
        return ResponseEntity.noContent().build();
    }
}
