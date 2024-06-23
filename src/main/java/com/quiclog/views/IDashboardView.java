package com.quiclog.views;

import java.util.List;

import com.quiclog.entities.IMetric;

public interface IDashboardView {
    void displayMetrics(List<IMetric> metrics);
    void displayStreaks(int streakCount);
}

