package com.apress.prospring5.ch8.sec1.service;

import com.apress.prospring5.ch8.sec1.view.SingerSummary;

import java.util.List;

public interface SingerSummaryService {
    List<SingerSummary> findAll();
}
