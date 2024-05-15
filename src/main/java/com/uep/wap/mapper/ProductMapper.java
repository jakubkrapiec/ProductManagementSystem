package com.uep.wap.mapper;

import com.uep.wap.dto.ProductDTO;
import com.uep.wap.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setOrderIds(product.getOrders().stream().map(Order::getOrderID).collect(Collectors.toList()));
        dto.setCustomerHistoryIds(product.getCustomerHistories().stream().map(CustomerHistory::getCustomerHistoryID).collect(Collectors.toList()));
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setAvailability(product.isAvailability());
        dto.setDescription(product.getDescription());
        dto.setWarehouseIds(product.getWarehouses().stream().map(Warehouse::getWarehouseId).collect(Collectors.toList()));
        dto.setSupplierId(product.getSupplier().getSupplierId());
        dto.setCategoryId(product.getCategory().getCategoryId());

        return dto;
    }

    public static Product toEntity(ProductDTO dto, List<Order> orders, List<CustomerHistory> customerHistories, List<Warehouse> warehouses, Supplier supplier, ProductCategory category) {
        if (dto == null) {
            return null;
        }

        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setOrders(orders);
        product.setCustomerHistories(customerHistories);
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setAvailability(dto.isAvailability());
        product.setDescription(dto.getDescription());
        product.setWarehouses(warehouses);
        product.setSupplier(supplier);
        product.setCategory(category);

        return product;
    }
}
