package com.quiclog.command;

import java.util.List;
import com.quiclog.model.Bucket;

public class CreateBucketCommand implements Command {
    private final List<Bucket> bucketList;
    private final Bucket newBucket;

    public CreateBucketCommand(List<Bucket> bucketList, String bucketName) {
        this.bucketList = bucketList;
        this.newBucket = new Bucket(bucketName);
    }

    @Override
    public void execute() {
        bucketList.add(newBucket);
    }

    @Override
    public void undo() {
        bucketList.remove(newBucket);
    }

    @Override
    public boolean isReversible() {
        return true;
    }

    public Bucket getCreatedBucket() {
        return newBucket;
    }
}

public class RenameBucketCommand implements Command {
    private final Bucket bucket;
    private final String oldName;
    private final String newName;

    public RenameBucketCommand(Bucket bucket, String newName) {
        this.bucket = bucket;
        this.oldName = bucket.getName();
        this.newName = newName;
    }

    @Override
    public void execute() {
        bucket.setName(newName);
    }

    @Override
    public void undo() {
        bucket.setName(oldName);
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}

public class DeleteBucketCommand implements Command {
    private final List<Bucket> bucketList;
    private final Bucket bucketToDelete;
    private final int originalIndex;

    public DeleteBucketCommand(List<Bucket> bucketList, Bucket bucketToDelete) {
        this.bucketList = bucketList;
        this.bucketToDelete = bucketToDelete;
        this.originalIndex = bucketList.indexOf(bucketToDelete);
    }

    @Override
    public void execute() {
        bucketList.remove(bucketToDelete);
    }

    @Override
    public void undo() {
        if (originalIndex >= 0 && originalIndex <= bucketList.size()) {
            bucketList.add(originalIndex, bucketToDelete);
        } else {
            bucketList.add(bucketToDelete);
        }
    }

    @Override
    public boolean isReversible() {
        return true;
    }
}