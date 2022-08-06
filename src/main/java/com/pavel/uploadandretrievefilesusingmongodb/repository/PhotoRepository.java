package com.pavel.uploadandretrievefilesusingmongodb.repository;

import com.pavel.uploadandretrievefilesusingmongodb.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
