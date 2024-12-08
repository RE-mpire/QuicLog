package com.logapp.command;

public interface Command {
    void execute();
    void undo();
    boolean isReversible();
}