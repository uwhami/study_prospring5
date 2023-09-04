package com.apress.prospring5.ch4.sec11.kindergarten;

import com.apress.prospring5.ch4.sec11.Food;
import com.apress.prospring5.ch4.sec11.FoodProviderService;

import java.util.ArrayList;
import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderService {

    @Override
    public List<Food> provideLunchSet() {
        List<Food> lunchSet = new ArrayList<>();
        lunchSet.add(new Food("Mile"));
        lunchSet.add(new Food("Biscuits"));
        return lunchSet;
    }
}
