package com.logapp.viewmodel;

import com.logapp.model.Bucket;
import com.logapp.model.Note;
import com.logapp.command.*;

public class NoteViewModel {
    private final CommandManager CommandManager;

    public NoteViewModel() {
        this.CommandManager = new CommandManager();
    }

    public Note addNote(Bucket bucket, String content) {
        AddNoteCommand command = new AddNoteCommand(bucket, content);
        CommandManager.executeCommand(command);
        return bucket.getNotes().get(bucket.getNotes().size() - 1);
    }

    public boolean editNote(Note note, String newContent) {
        EditNoteCommand command = new EditNoteCommand(note, newContent);
        CommandManager.executeCommand(command);
        return true;
    }

    public boolean deleteNote(Bucket bucket, Note note) {
        if (bucket.getNotes().contains(note)) {
            bucket.removeNote(note);
            return true;
        }
        return false;
    }

    public List<Note> searchNotes(Bucket bucket, String searchTerm) {
        return bucket.getNotes().stream()
            .filter(note -> note.getContent().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean undo() {
        return CommandManager.undo();
    }

    public boolean redo() {
        return CommandManager.redo();
    }
}