package com.semanta.share.repository;

import com.semanta.share.model.ShareAccessedFrom;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareAccessedFromRepository extends MongoRepository<ShareAccessedFrom, String> {
}