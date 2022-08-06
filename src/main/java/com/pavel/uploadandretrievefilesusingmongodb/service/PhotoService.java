package com.pavel.uploadandretrievefilesusingmongodb.service;

import com.pavel.uploadandretrievefilesusingmongodb.model.Photo;
import com.pavel.uploadandretrievefilesusingmongodb.repository.PhotoRepository;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public String addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepository.insert(photo);
        return photo.getId();
    }

    public Photo getPhoto(String id) {
        return photoRepository.findById(id).get();
    }
}
