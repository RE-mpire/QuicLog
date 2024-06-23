package com.quiclog.factories;

import java.util.Map;

import com.quiclog.entities.IMetric;
import com.quiclog.entities.IQuestion;
import com.quiclog.entities.IQuestionnaire;
import com.quiclog.entities.Questionnaire;

public class QuestionnaireFactory {
    public static IQuestionnaire createQuestionnaire(String name, Map<IQuestion, IMetric> questionMetricPairs) {
        IQuestionnaire questionnaire = new Questionnaire();
        questionnaire.setName(name);
        questionnaire.setQuestionMetricPairs(questionMetricPairs);
        return questionnaire;
    }
}

