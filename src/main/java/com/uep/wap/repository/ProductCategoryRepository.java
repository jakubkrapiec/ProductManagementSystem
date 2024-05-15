package com.uep.wap.repository;

import com.uep.wap.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {
}
