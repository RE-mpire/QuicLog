package com.quiclog.entities;

public class Question implements IQuestion {
    private String prompt;
    private QuestionType type;

    @Override
    public String getPrompt() {
        return prompt;
    }

    @Override
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public QuestionType getType() {
        return type;
    }

    @Override
    public void setType(QuestionType type) {
        this.type = type;
    }
}

