import com.quiclog.command.Command;
import com.quiclog.command.CommandManager;

public class Main {
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();

        Command exampleCommand = new Command() {
            @Override
            public void execute() {
                System.out.println("Executing command...");
            }
        };

        commandManager.executeCommand(exampleCommand);
        commandManager.undo();
        commandManager.redo();
    }
}