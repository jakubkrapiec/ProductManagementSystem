package com.uep.wap.mapper;

import com.uep.wap.dto.ProductCategoryDTO;
import com.uep.wap.model.Product;
import com.uep.wap.model.ProductCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ProductCategoryMapper {

    public static ProductCategoryDTO toDTO(ProductCategory category) {
        if (category == null) {
            return null;
        }

        ProductCategoryDTO dto = new ProductCategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setDescription(category.getDescription());
        dto.setProductIds(category.getProducts().stream().map(Product::getProductId).collect(Collectors.toList()));

        return dto;
    }

    public static ProductCategory toEntity(ProductCategoryDTO dto, List<Product> products) {
        if (dto == null) {
            return null;
        }

        ProductCategory category = new ProductCategory();
        category.setCategoryId(dto.getCategoryId());
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        category.setProducts(products);

        return category;
    }
}
