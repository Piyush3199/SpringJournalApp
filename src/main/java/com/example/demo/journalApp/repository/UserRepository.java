package com.example.demo.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User, ObjectId>{

    User findByUserName(String userName);
}
