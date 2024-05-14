package com.uep.wap.repository;

import com.uep.wap.model.Return;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends CrudRepository<Return, Long> {
}
