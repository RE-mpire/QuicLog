package com.quiclog.entities;

public class Action implements IAction {
    private final ActionType actionType;
    private final Object targetEntity;
    private final Object previousState;
    private final Object newState;

    public Action(ActionType actionType, Object targetEntity, Object previousState, Object newState) {
        this.actionType = actionType;
        this.targetEntity = targetEntity;
        this.previousState = previousState;
        this.newState = newState;
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public Object getTargetEntity() {
        return targetEntity;
    }

    @Override
    public Object getPreviousState() {
        return previousState;
    }

    @Override
    public Object getNewState() {
        return newState;
    }
}

