package com.quiclog.factories;

import com.quiclog.entities.IQuestion;
import com.quiclog.entities.Question;

public class QuestionFactory {
    public static IQuestion createQuestion(String prompt, IQuestion.QuestionType type) {
        IQuestion question = new Question();
        question.setPrompt(prompt);
        question.setType(type);
        return question;
    }
}
