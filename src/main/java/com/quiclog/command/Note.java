package com.logapp.command;

import java.util.List;
import com.logapp.model.Note;

public class AddNoteCommand implements Command {
    private final Bucket bucket;
    private final Note note;

    public AddNoteCommand(Bucket bucket, String content) {
        this.bucket = bucket;
        this.note = new Note(content);
    }

    @Override
    public void execute() {
        bucket.addNote(note);
    }

    @Override
    public void undo() {
        bucket.removeNote(note);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}

public class EditNoteCommand implements Command {
    private final Note note;
    private final String oldContent;
    private final String newContent;

    public EditNoteCommand(Note note, String newContent) {
        this.note = note;
        this.oldContent = note.getContent();
        this.newContent = newContent;
    }

    @Override
    public void execute() {
        note.setContent(newContent);
    }

    @Override
    public void undo() {
        note.setContent(oldContent);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}