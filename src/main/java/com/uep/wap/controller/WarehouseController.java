package com.uep.wap.controller;

import com.uep.wap.dto.WarehouseDTO;
import com.uep.wap.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<WarehouseDTO> addWarehouse(@RequestBody WarehouseDTO dto) {
        return ResponseEntity.status(201).body(warehouseService.addWarehouse(dto));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDTO>> getAllWarehouses() {
        List<WarehouseDTO> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> getWarehouseById(@PathVariable Long id) {
        WarehouseDTO dto = warehouseService.getWarehouseById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseDTO> updateWarehouse(@PathVariable Long id, @RequestBody WarehouseDTO dto) {
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.noContent().build();
    }
}
