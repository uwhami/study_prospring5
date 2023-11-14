package com.apress.prospring5.ch8.sec8.services;

import com.apress.prospring5.ch8.sec8.entities.SingerAudit;

import java.util.List;

public interface SingerAuditService {
    List<SingerAudit> findAll();
    SingerAudit findById(Long id);
    SingerAudit save(SingerAudit singer);
    SingerAudit findAuditByRevision(Long id, int revision);
}
