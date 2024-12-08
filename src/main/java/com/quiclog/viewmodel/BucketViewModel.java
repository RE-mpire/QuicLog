package com.logapp.viewmodel;

import com.logapp.model.Bucket;
import com.logapp.command.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.UUID;

public class BucketViewModel {
    private final List<Bucket> buckets;
    private final CommandManager CommandManager;

    public BucketViewModel() {
        this.buckets = new ArrayList<>();
        this.CommandManager = new CommandManager();
    }

    public Bucket createBucket(String bucketName) {
        CreateBucketCommand command = new CreateBucketCommand(buckets, bucketName);
        CommandManager.executeCommand(command);
        return command.getCreatedBucket();
    }

    public Bucket renameBucket(Bucket bucket, String newBucketName) {
        RenameBucketCommand command = new RenameBucketCommand(buckets, bucket, newBucketName);
        CommandManager.executeCommand(command);
        return command.getRenamedBucket();
    }

    public boolean deleteBucket(UUID bucketId) {
        Optional<Bucket> bucketToDelete = findBucketById(bucketId);
        
        if (bucketToDelete.isPresent()) {
            DeleteBucketCommand command = new DeleteBucketCommand(buckets, bucketToDelete.get());
            CommandManager.executeCommand(command);
            return true;
        }
        
        return false;
    }

    public Optional<Bucket> findBucketById(UUID bucketId) {
        return buckets.stream()
            .filter(bucket -> bucket.getId().equals(bucketId))
            .findFirst();
    }

    public List<Bucket> getAllBuckets() {
        return new ArrayList<>(buckets);
    }

    public List<Bucket> searchBucketsByName(String searchTerm) {
        return buckets.stream()
            .filter(bucket -> bucket.getName().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }

    public boolean undo() {
        return CommandManager.undo();
    }

    public boolean redo() {
        return CommandManager.redo();
    }
}