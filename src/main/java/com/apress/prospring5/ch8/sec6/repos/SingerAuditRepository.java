package com.apress.prospring5.ch8.sec6.repos;

import com.apress.prospring5.ch8.sec6.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
