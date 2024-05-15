package com.uep.wap.service;

import com.uep.wap.dto.ProductDTO;
import com.uep.wap.mapper.ProductMapper;
import com.uep.wap.model.*;
import com.uep.wap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerHistoryRepository customerHistoryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public ProductDTO addProduct(ProductDTO dto) {
        List<Order> orders = StreamSupport.stream(orderRepository.findAllById(dto.getOrderIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<CustomerHistory> customerHistories = StreamSupport.stream(customerHistoryRepository.findAllById(dto.getCustomerHistoryIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Warehouse> warehouses = StreamSupport.stream(warehouseRepository.findAllById(dto.getWarehouseIds()).spliterator(), false)
                .collect(Collectors.toList());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        ProductCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product product = ProductMapper.toEntity(dto, orders, customerHistories, warehouses, supplier, category);
        return ProductMapper.toDTO(productRepository.save(product));
    }

    public List<ProductDTO> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.toDTO(product);
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Order> orders = StreamSupport.stream(orderRepository.findAllById(dto.getOrderIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<CustomerHistory> customerHistories = StreamSupport.stream(customerHistoryRepository.findAllById(dto.getCustomerHistoryIds()).spliterator(), false)
                .collect(Collectors.toList());
        List<Warehouse> warehouses = StreamSupport.stream(warehouseRepository.findAllById(dto.getWarehouseIds()).spliterator(), false)
                .collect(Collectors.toList());
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        ProductCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product updatedProduct = ProductMapper.toEntity(dto, orders, customerHistories, warehouses, supplier, category);
        updatedProduct.setProductId(existingProduct.getProductId());
        return ProductMapper.toDTO(productRepository.save(updatedProduct));
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
