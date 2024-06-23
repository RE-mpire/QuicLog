package com.quiclog.controllers;

import com.quiclog.entities.IAction;

public interface IActionLog {
    void pushAction(IAction action);
    IAction popAction();
    boolean isEmpty();
}

