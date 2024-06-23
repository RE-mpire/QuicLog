package com.quiclog.views;

import com.quiclog.entities.IQuestionnaire;

public interface IQuestionnaireCreationView {
    void displayQuestionnaireForm(IQuestionnaire questionnaire);
    void showSuccessMessage(String message);
    void showErrorMessage(String message);
}

