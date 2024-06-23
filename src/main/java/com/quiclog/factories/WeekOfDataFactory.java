package com.quiclog.factories;

import java.util.List;

import com.quiclog.entities.IDataPoint;
import com.quiclog.entities.IWeekOfData;
import com.quiclog.entities.WeekOfData;

public class WeekOfDataFactory {
    public static IWeekOfData createWeekOfData(List<IDataPoint> dataPoints) {
        IWeekOfData weekOfData = new WeekOfData();
        weekOfData.setDataPoints(dataPoints);
        return weekOfData;
    }
}

