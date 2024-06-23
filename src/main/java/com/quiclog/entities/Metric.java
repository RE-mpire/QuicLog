package com.quiclog.entities;

import java.util.List;

public class Metric implements IMetric {
    private String title;
    private String description;
    private int min;
    private int max;
    private List<IYearOfData> yearOfData;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getMin() {
        return min;
    }

    @Override
    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public List<IYearOfData> getYearOfData() {
        return yearOfData;
    }

    @Override
    public void setYearOfData(List<IYearOfData> yearOfData) {
        this.yearOfData = yearOfData;
    }
}

