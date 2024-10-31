package com.compasso.msnotify.repository;

import com.compasso.msnotify.entities.Notify;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotifyRepository extends MongoRepository<Notify, String> {
}
