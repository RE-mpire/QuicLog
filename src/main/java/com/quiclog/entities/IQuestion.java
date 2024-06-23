package com.quiclog.entities;

public interface IQuestion {
    String getPrompt();
    void setPrompt(String prompt);
    
    QuestionType getType();
    void setType(QuestionType type);
    
    enum QuestionType {
        SCALE, TEXT_FIELD, NUM_FIELD
    }
}

