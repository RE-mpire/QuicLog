package com.quiclog.views;

import com.quiclog.entities.IQuestionnaire;

public interface IQuestionnairePageView {
    void displayQuestionnaire(IQuestionnaire questionnaire);
    void showSuccessMessage(String message);
    void showErrorMessage(String message);
}

