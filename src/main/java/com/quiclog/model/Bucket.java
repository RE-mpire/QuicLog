package com.quiclog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bucket {
    private final UUID id;
    private String name;
    private final LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private final List<Note> notes;

    public Bucket(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = createdAt;
        this.notes = new ArrayList<>();
    }

    // Methods to manage notes
    public void addNote(Note note) {
        notes.add(note);
        updateLastModified();
    }

    public void removeNote(Note note) {
        notes.remove(note);
        updateLastModified();
    }

    private void updateLastModified() {
        this.lastModifiedAt = LocalDateTime.now();
    }

    // Getters and setters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { 
        this.name = name;
        updateLastModified();
    }
    public List<Note> getNotes() { return new ArrayList<>(notes); }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastModifiedAt() { return lastModifiedAt; }
}