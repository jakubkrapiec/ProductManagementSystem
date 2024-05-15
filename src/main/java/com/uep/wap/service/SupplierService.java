package com.uep.wap.service;

import com.uep.wap.dto.SupplierDTO;
import com.uep.wap.mapper.SupplierMapper;
import com.uep.wap.model.Product;
import com.uep.wap.model.Supplier;
import com.uep.wap.repository.ProductRepository;
import com.uep.wap.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    public SupplierDTO addSupplier(SupplierDTO dto) {
        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());
        Supplier supplier = SupplierMapper.toEntity(dto, products);
        return SupplierMapper.toDTO(supplierRepository.save(supplier));
    }

    public List<SupplierDTO> getAllSuppliers() {
        return StreamSupport.stream(supplierRepository.findAll().spliterator(), false)
                .map(SupplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SupplierDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return SupplierMapper.toDTO(supplier);
    }

    public SupplierDTO updateSupplier(Long id, SupplierDTO dto) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());

        Supplier updatedSupplier = SupplierMapper.toEntity(dto, products);
        updatedSupplier.setSupplierId(existingSupplier.getSupplierId());
        return SupplierMapper.toDTO(supplierRepository.save(updatedSupplier));
    }

    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        supplierRepository.delete(supplier);
    }
}
