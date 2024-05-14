package com.uep.wap.repository;

import com.uep.wap.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends CrudRepository<Client, Long> {
}
