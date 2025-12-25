package com.photosharing.repositories;

import com.photosharing.models.Photo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryPhotoRepository implements PhotoRepository {
    private final Map<String, Photo> photos;
    private final Map<String, List<String>> userPhotosIndex;

    public InMemoryPhotoRepository() {
        this.photos = new ConcurrentHashMap<>();
        this.userPhotosIndex = new ConcurrentHashMap<>();
    }

    @Override
    public Photo save(Photo photo) {
        photos.put(photo.getPhotoId(), photo);
        
        userPhotosIndex.computeIfAbsent(photo.getPostedByUserId(), k -> new ArrayList<>())
                      .add(photo.getPhotoId());
        
        return photo;
    }

    @Override
    public Optional<Photo> findById(String photoId) {
        return Optional.ofNullable(photos.get(photoId));
    }

    @Override
    public List<Photo> findByUserId(String userId) {
        List<String> photoIds = userPhotosIndex.getOrDefault(userId, Collections.emptyList());
        return photoIds.stream()
                      .map(photos::get)
                      .filter(Objects::nonNull)
                      .collect(Collectors.toList());
    }

    @Override
    public List<Photo> findByTaggedUserId(String userId) {
        return photos.values().stream()
                    .filter(photo -> photo.hasTag(userId))
                    .collect(Collectors.toList());
    }

    @Override
    public List<Photo> findAll() {
        return new ArrayList<>(photos.values());
    }

    @Override
    public boolean deleteById(String photoId) {
        Photo photo = photos.remove(photoId);
        if (photo != null) {
            // Remove from index
            List<String> userPhotos = userPhotosIndex.get(photo.getPostedByUserId());
            if (userPhotos != null) {
                userPhotos.remove(photoId);
            }
            return true;
        }
        return false;
    }
}

