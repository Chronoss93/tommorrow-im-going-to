package com.tgt.group;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GroupRepository extends MongoRepository<Group, ObjectId> {
     Optional<Group> findByName(String name);
}
