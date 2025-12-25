package com.photosharing.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Photo {
    private final String photoId;
    private final String photoUrl;
    private final String postedByUserId;
    private final List<String> taggedUserIds;
    private final LocalDateTime timestamp;

    public Photo(String photoId, String photoUrl, String postedByUserId, List<String> taggedUserIds) {
        this.photoId = photoId;
        this.photoUrl = photoUrl;
        this.postedByUserId = postedByUserId;
        this.taggedUserIds = new ArrayList<>(taggedUserIds);
        this.timestamp = LocalDateTime.now();
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getPostedByUserId() {
        return postedByUserId;
    }

    public List<String> getTaggedUserIds() {
        return new ArrayList<>(taggedUserIds);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean hasTag(String userId) {
        return taggedUserIds.contains(userId);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "photoId='" + photoId + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", postedBy='" + postedByUserId + '\'' +
                ", tags=" + taggedUserIds.size() +
                ", timestamp=" + timestamp +
                '}';
    }
}

