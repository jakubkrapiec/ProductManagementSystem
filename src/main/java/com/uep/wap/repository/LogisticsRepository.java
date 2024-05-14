package com.uep.wap.repository;

import com.uep.wap.model.Logistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsRepository  extends CrudRepository<Logistics, Long> {
}
