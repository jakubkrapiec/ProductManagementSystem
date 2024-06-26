package com.uep.wap.repository;

import com.uep.wap.model.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository  extends CrudRepository<Invoice, Long> {
}
