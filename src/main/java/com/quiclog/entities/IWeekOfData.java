package com.quiclog.entities;

import java.util.List;

public interface IWeekOfData {
    List<IDataPoint> getDataPoints();
    void setDataPoints(List<IDataPoint> dataPoints);
}

