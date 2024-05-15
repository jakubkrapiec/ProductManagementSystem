package com.uep.wap.repository;

import com.uep.wap.model.Supplier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
