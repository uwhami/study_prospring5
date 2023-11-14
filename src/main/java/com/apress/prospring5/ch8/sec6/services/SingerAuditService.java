package com.apress.prospring5.ch8.sec6.services;

import com.apress.prospring5.ch8.sec6.entities.SingerAudit;

import java.util.List;

public interface SingerAuditService {
    List<SingerAudit> findAll();
    SingerAudit findById(Long id);
    SingerAudit save(SingerAudit singer);
}
