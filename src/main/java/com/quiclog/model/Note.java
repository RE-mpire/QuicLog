package com.quiclog.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Note {
    private final UUID id;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    public Note(String content) {
        this.id = UUID.randomUUID();
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.lastModifiedAt = createdAt;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public String getContent() { return content; }
    public void setContent(String content) { 
        this.content = content;
        this.lastModifiedAt = LocalDateTime.now();
    }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getLastModifiedAt() { return lastModifiedAt; }
}