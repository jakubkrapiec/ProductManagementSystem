package com.uep.wap.service;

import com.uep.wap.dto.WarehouseDTO;
import com.uep.wap.mapper.WarehouseMapper;
import com.uep.wap.model.Logistics;
import com.uep.wap.model.Product;
import com.uep.wap.model.Warehouse;
import com.uep.wap.repository.LogisticsRepository;
import com.uep.wap.repository.ProductRepository;
import com.uep.wap.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LogisticsRepository logisticsRepository;

    public WarehouseDTO addWarehouse(WarehouseDTO dto) {
        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Logistics> shipments = StreamSupport.stream(logisticsRepository.findAllById(dto.getShipmentIds()).spliterator(), false)
                .collect(Collectors.toList());
        Warehouse warehouse = WarehouseMapper.toEntity(dto, products, shipments);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return WarehouseMapper.toDTO(savedWarehouse);
    }

    public List<WarehouseDTO> getAllWarehouses() {
        return StreamSupport.stream(warehouseRepository.findAll().spliterator(), false)
                .map(WarehouseMapper::toDTO)
                .collect(Collectors.toList());
    }

    public WarehouseDTO getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return WarehouseMapper.toDTO(warehouse);
    }

    public WarehouseDTO updateWarehouse(Long id, WarehouseDTO dto) {
        Warehouse existingWarehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Logistics> shipments = StreamSupport.stream(logisticsRepository.findAllById(dto.getShipmentIds()).spliterator(), false)
                .collect(Collectors.toList());

        Warehouse updatedWarehouse = WarehouseMapper.toEntity(dto, products, shipments);
        updatedWarehouse.setWarehouseId(existingWarehouse.getWarehouseId());
        return WarehouseMapper.toDTO(warehouseRepository.save(updatedWarehouse));
    }

    public void deleteWarehouse(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        warehouseRepository.delete(warehouse);
    }
}
