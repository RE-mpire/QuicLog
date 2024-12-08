# QuicLog

A rewrite of my [CSC207 project](https://github.com/CSC207-2022F-UofT/course-project-mewdy) using better design pricipals and a revised functionality.

## Why Rewrite an Old Project?

While the original project earned a good grade and was well-executed, its design was heavily influenced by the need to demonstrate specific design patterns as part of a course requirement. These constraints, while educational, somewhat compromised the overall quality and cohesion of the application. I saw this as an opportunity to revisit the project as a fun side endeavor, free from those limitations. This time, my focus is on building something that not only reflects my skills but also meets my own standards of satisfaction and craftsmanship.

## Key Design Principles

### 1. MVVM Pattern Implementation
- **Model**: `model/` package
  - Pure data classes representing core entities
  - No business logic or UI concerns
  - Represents the application's data structure

- **ViewModel**: `viewmodel/` package
  - Transforms Model data for View presentation
  - Contains UI state logic
  - Mediates between Model and View
  - Manages Command execution and history

- **View**: `view/` package
  - Responsible for rendering UI
  - Minimal logic, primarily presentation
  - Binds to ViewModel for data and actions

### 2. Command Pattern Integration
- Maintains ability to undo/redo actions
- Each command implements:
  - `execute()` method
  - `undo()` method
- `CommandInvoker` manages command stack
- Enables complex state management with precise tracking

### 3. Service Layer Responsibilities
- Implements core business logic
- Orchestrates operations between models
- Provides abstraction for complex operations

## Sample Workflow
1. User creates a new bucket via UI
2. ConsoleView calls BucketViewModel
3. BucketViewModel creates CreateBucketCommand
4. CommandInvoker executes and tracks the command
5. Underlying BucketService performs actual creation
6. ViewModel updates to reflect new state

## Future Extensibility
- Easy to add new command types
- Simple to implement different UI (console/graphical)
- Clear separation of concerns


## Core Interfaces and Abstract Classes

### Command Interface
```java
public interface Command {
    void execute();
    void undo();
    boolean isReversible();
}
```

### Abstract ViewModel
```java
public abstract class BaseViewModel {
    protected CommandInvoker commandInvoker;
    
    public void executeCommand(Command command) {
        commandInvoker.executeCommand(command);
    }
    
    public void undoLastCommand() {
        commandInvoker.undo();
    }
    
    public void redoLastCommand() {
        commandInvoker.redo();
    }
}
```

## Benefits of This Approach
1. Highly modular and extensible architecture
2. Clear separation of concerns
3. Robust undo/redo functionality
4. Easy to test individual components
5. Supports future UI expansion

