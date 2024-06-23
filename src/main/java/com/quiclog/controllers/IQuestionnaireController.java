package com.quiclog.controllers;

import java.util.List;

import com.quiclog.entities.IQuestionnaire;

public interface IQuestionnaireController {
    void addQuestionnaire(IQuestionnaire questionnaire);
    void deleteQuestionnaire(IQuestionnaire questionnaire);
    void replaceQuestionnaire(IQuestionnaire oldQuestionnaire, IQuestionnaire newQuestionnaire);
    List<IQuestionnaire> getQuestionnaires();
}

