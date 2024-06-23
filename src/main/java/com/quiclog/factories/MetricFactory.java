package com.quiclog.factories;

import java.util.List;

import com.quiclog.entities.IMetric;
import com.quiclog.entities.IYearOfData;
import com.quiclog.entities.Metric;

public class MetricFactory {
    public static IMetric createMetric(String title, String description, int min, int max, List<IYearOfData> yearOfData) {
        IMetric metric = new Metric();
        metric.setTitle(title);
        metric.setDescription(description);
        metric.setMin(min);
        metric.setMax(max);
        metric.setYearOfData(yearOfData);
        return metric;
    }
}
