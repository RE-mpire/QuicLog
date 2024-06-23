package com.quiclog.entities;

public interface IAction {
    ActionType getActionType();
    Object getTargetEntity();
    Object getPreviousState();
    Object getNewState();
    
    enum ActionType {
        ADD, DELETE, REPLACE
    }
}

