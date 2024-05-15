package com.uep.wap.controller;

import com.uep.wap.dto.ProductCategoryDTO;
import com.uep.wap.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategoryDTO> addProductCategory(@RequestBody ProductCategoryDTO dto) {
        return ResponseEntity.status(201).body(productCategoryService.addProductCategory(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategories() {
        List<ProductCategoryDTO> categories = productCategoryService.getAllProductCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable Long id) {
        ProductCategoryDTO dto = productCategoryService.getProductCategoryById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductCategoryDTO> updateProductCategory(@PathVariable Long id, @RequestBody ProductCategoryDTO dto) {
        return ResponseEntity.ok(productCategoryService.updateProductCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
        return ResponseEntity.noContent().build();
    }
}
