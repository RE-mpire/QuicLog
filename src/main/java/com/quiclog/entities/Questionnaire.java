package com.quiclog.entities;

import java.util.Map;

public class Questionnaire implements IQuestionnaire {
    private String name;
    private Map<IQuestion, IMetric> questionMetricPairs;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<IQuestion, IMetric> getQuestionMetricPairs() {
        return questionMetricPairs;
    }

    @Override
    public void setQuestionMetricPairs(Map<IQuestion, IMetric> questionMetricPairs) {
        this.questionMetricPairs = questionMetricPairs;
    }
}
