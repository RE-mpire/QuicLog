package com.quiclog.command;

public class CommandManager {
    private final Deque<Command> executedCommands = new LinkedList<>();
    private final Deque<Command> undoneCommands = new LinkedList<>();

    public void executeCommand(Command command) {
        command.execute();
        executedCommands.push(command);
        undoneCommands.clear(); // Clear redo stack when a new command is executed
    }

    public boolean undo() {
        if (executedCommands.isEmpty()) return false;
        
        Command command = executedCommands.pop();
        if (command.isReversible()) {
            command.undo();
            undoneCommands.push(command);
            return true;
        }
        return false;
    }

    public boolean redo() {
        if (undoneCommands.isEmpty()) return false;
        
        Command command = undoneCommands.pop();
        command.execute();
        executedCommands.push(command);
        return true;
    }

    public int getCommandHistorySize() {
        return executedCommands.size();
    }

    public int getUndoneCommandsSize() {
        return undoneCommands.size();
    }
}