package com.logapp.viewmodel;

import com.logapp.model.Bucket;
import com.logapp.model.Note;

public class ApplicationViewModel {
    private final BucketViewModel bucketViewModel;
    private final NoteViewModel noteViewModel;

    public ApplicationViewModel() {
        this.bucketViewModel = new BucketViewModel();
        this.noteViewModel = new NoteViewModel();
    }

    public BucketViewModel getBucketViewModel() {
        return bucketViewModel;
    }

    public NoteViewModel getNoteViewModel() {
        return noteViewModel;
    }

    public boolean undo() {
        return bucketViewModel.undo() || noteViewModel.undo();
    }

    public boolean redo() {
        return bucketViewModel.redo() || noteViewModel.redo();
    }

    public Bucket createBucket(String bucketName) {
        return bucketViewModel.createBucket(bucketName);
    }

    public Bucket renameBucket(String oldBucketName, String newBucketName) {
        Bucket bucket = bucketViewModel.searchBucketsByName(oldBucketName)
            .stream()
            .findFirst()
            .orElse(null);

        if (bucket == null) {
            return null;
        }

        return bucketViewModel.renameBucket(bucket, newBucketName);
    }

    public boolean deleteBucket(String bucketName) {
        return bucketViewModel.deleteBucket(bucketName);
    }

    public Note createNoteInBucket(String bucketName, String noteContent) {
        Bucket bucket = bucketViewModel.searchBucketsByName(bucketName)
            .stream()
            .findFirst()
            .orElse(bucketViewModel.createBucket(bucketName));

        return noteViewModel.addNote(bucket, noteContent);
    }

    public boolean editNoteInBucket(String bucketName, String noteContent, String newContent) {
        Bucket bucket = bucketViewModel.searchBucketsByName(bucketName)
            .stream()
            .findFirst()
            .orElse(null);

        if (bucket == null) {
            return false;
        }

        Note note = noteViewModel.searchNotes(bucket, noteContent)
            .stream()
            .findFirst()
            .orElse(null);

        if (note == null) {
            return false;
        }

        return noteViewModel.editNote(note, newContent);
    }

    public boolean deleteNoteInBucket(String bucketName, String noteContent) {
        Bucket bucket = bucketViewModel.searchBucketsByName(bucketName)
            .stream()
            .findFirst()
            .orElse(null);

        if (bucket == null) {
            return false;
        }

        Note note = noteViewModel.searchNotes(bucket, noteContent)
            .stream()
            .findFirst()
            .orElse(null);

        if (note == null) {
            return false;
        }

        return noteViewModel.deleteNote(bucket, note);
    }

    public List<Bucket> searchBucketsByName(String searchTerm) {
        return bucketViewModel.searchBucketsByName(searchTerm);
    }

    public List<Note> searchNotes(String searchTerm) {
        return noteViewModel.searchNotes(searchTerm);
    }
}