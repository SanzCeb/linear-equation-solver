package solver.matrix;

import java.util.Stack;

public class CommandHistory {
    private final Stack<SwapCommand> commandHistory;

    public CommandHistory() {
        this.commandHistory = new Stack<>();
    }

    public void pushCommand(SwapCommand command) {
        commandHistory.push(command);
    }

    public SwapCommand popCommand() {
        return commandHistory.empty() ? null : commandHistory.pop();
    }

    public SwapCommand lastCommand() {
        return commandHistory.empty() ? null : commandHistory.peek();
    }

    public boolean isNotEmpty() {
        return !commandHistory.isEmpty();
    }
}
