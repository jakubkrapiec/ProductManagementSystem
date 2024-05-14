package com.uep.wap.repository;

import com.uep.wap.model.CustomerHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerHistoryRepository extends CrudRepository<CustomerHistory, Long> {
}
