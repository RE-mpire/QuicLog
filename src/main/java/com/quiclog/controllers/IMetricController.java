package com.quiclog.controllers;

import java.util.List;

import com.quiclog.entities.IMetric;

public interface IMetricController {
    void addMetric(IMetric metric);
    void deleteMetric(IMetric metric);
    void replaceMetric(IMetric oldMetric, IMetric newMetric);
    List<IMetric> getMetrics();
}

