package com.photosharing.repositories;

import com.photosharing.models.Photo;

import java.util.List;
import java.util.Optional;


public interface PhotoRepository {

    Photo save(Photo photo);


    Optional<Photo> findById(String photoId);

    List<Photo> findByUserId(String userId);

    List<Photo> findByTaggedUserId(String userId);

    List<Photo> findAll();

    boolean deleteById(String photoId);
}

