package com.uep.wap.service;

import com.uep.wap.dto.ProductCategoryDTO;
import com.uep.wap.mapper.ProductCategoryMapper;
import com.uep.wap.model.Product;
import com.uep.wap.model.ProductCategory;
import com.uep.wap.repository.ProductCategoryRepository;
import com.uep.wap.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductCategoryDTO addProductCategory(ProductCategoryDTO dto) {
        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());
        ProductCategory category = ProductCategoryMapper.toEntity(dto, products);
        return ProductCategoryMapper.toDTO(productCategoryRepository.save(category));
    }

    public List<ProductCategoryDTO> getAllProductCategories() {
        return StreamSupport.stream(productCategoryRepository.findAll().spliterator(), false)
                .map(ProductCategoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProductCategoryDTO getProductCategoryById(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found"));
        return ProductCategoryMapper.toDTO(category);
    }

    public ProductCategoryDTO updateProductCategory(Long id, ProductCategoryDTO dto) {
        ProductCategory existingCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found"));

        List<Product> products = StreamSupport.stream(productRepository.findAllById(dto.getProductIds()).spliterator(), false)
                .collect(Collectors.toList());

        ProductCategory updatedCategory = ProductCategoryMapper.toEntity(dto, products);
        updatedCategory.setCategoryId(existingCategory.getCategoryId());
        return ProductCategoryMapper.toDTO(productCategoryRepository.save(updatedCategory));
    }

    public void deleteProductCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductCategory not found"));
        productCategoryRepository.delete(category);
    }
}
