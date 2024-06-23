package com.quiclog.entities;

import java.util.Map;

public interface IQuestionnaire {
    String getName();
    void setName(String name);
    
    Map<IQuestion, IMetric> getQuestionMetricPairs();
    void setQuestionMetricPairs(Map<IQuestion, IMetric> questionMetricPairs);
}

