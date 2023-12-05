package com.apress.prospring5.ch15;

import com.apress.prospring5.ch15.services.SingerService;
import org.hibernate.envers.Audited;

public class AppStatisticsImpl implements AppStatistics{
    @Audited
    private SingerService singerService;

    @Override
    public int getTotalSingerCount() {
        return singerService.findAll().size();
    }
}
