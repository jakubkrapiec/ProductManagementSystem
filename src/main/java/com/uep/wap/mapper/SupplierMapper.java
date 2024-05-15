package com.uep.wap.mapper;

import com.uep.wap.dto.SupplierDTO;
import com.uep.wap.model.Product;
import com.uep.wap.model.Supplier;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierMapper {

    public static SupplierDTO toDTO(Supplier supplier) {
        if (supplier == null) {
            return null;
        }

        SupplierDTO dto = new SupplierDTO();
        dto.setSupplierId(supplier.getSupplierId());
        dto.setCompanyName(supplier.getCompanyName());
        dto.setContactName(supplier.getContactName());
        dto.setPhoneNumber(supplier.getPhoneNumber());
        dto.setProductIds(supplier.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()));

        return dto;
    }

    public static Supplier toEntity(SupplierDTO dto, List<Product> products) {
        if (dto == null) {
            return null;
        }

        Supplier supplier = new Supplier();
        supplier.setSupplierId(dto.getSupplierId());
        supplier.setCompanyName(dto.getCompanyName());
        supplier.setContactName(dto.getContactName());
        supplier.setPhoneNumber(dto.getPhoneNumber());
        supplier.setProducts(products);

        return supplier;
    }
}
