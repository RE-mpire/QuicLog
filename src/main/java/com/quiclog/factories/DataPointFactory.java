package com.quiclog.factories;

import java.time.LocalDateTime;

import com.quiclog.entities.DataPoint;
import com.quiclog.entities.IDataPoint;

public class DataPointFactory {
    public static IDataPoint createDataPoint(Object data, LocalDateTime dateTime) {
        IDataPoint dataPoint = new DataPoint();
        dataPoint.setData(data);
        dataPoint.setDateTime(dateTime);
        return dataPoint;
    }
}

