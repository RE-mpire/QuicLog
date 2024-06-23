package com.quiclog.entities;

import java.util.List;

public class WeekOfData implements IWeekOfData {
    private List<IDataPoint> dataPoints;

    @Override
    public List<IDataPoint> getDataPoints() {
        return dataPoints;
    }

    @Override
    public void setDataPoints(List<IDataPoint> dataPoints) {
        this.dataPoints = dataPoints;
    }
}

