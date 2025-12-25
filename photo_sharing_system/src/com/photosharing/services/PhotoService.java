package com.photosharing.services;

import com.photosharing.exceptions.InvalidInputException;
import com.photosharing.exceptions.NotFoundException;
import com.photosharing.models.Photo;
import com.photosharing.repositories.PhotoRepository;
import com.photosharing.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

public class PhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public PhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public Photo postPhoto(String userId, String photoUrl, List<String> taggedUserIds) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User", userId);
        }

        if (photoUrl == null || photoUrl.trim().isEmpty()) {
            throw new InvalidInputException("Photo URL cannot be empty");
        }

        for (String taggedUserId : taggedUserIds) {
            if (!userRepository.existsById(taggedUserId)) {
                throw new InvalidInputException("Cannot tag non-existent user: " + taggedUserId);
            }
        }

        String photoId = UUID.randomUUID().toString();
        Photo photo = new Photo(photoId, photoUrl, userId, taggedUserIds);
        
        return photoRepository.save(photo);
    }

    public Photo getPhotoById(String photoId) {
        return photoRepository.findById(photoId)
                .orElseThrow(() -> new NotFoundException("Photo", photoId));
    }

    public List<Photo> getPhotosByUser(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User", userId);
        }
        return photoRepository.findByUserId(userId);
    }

    public List<Photo> getPhotosWhereUserIsTagged(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("User", userId);
        }
        return photoRepository.findByTaggedUserId(userId);
    }

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}
