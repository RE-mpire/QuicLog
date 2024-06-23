package com.quiclog.views;

import com.quiclog.entities.IMetric;

public interface IMetricView {
    void displayMetricDetails(IMetric metric);
    void showErrorMessage(String message);
}

