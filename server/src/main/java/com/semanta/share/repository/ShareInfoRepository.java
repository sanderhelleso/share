package com.semanta.share.repository;

import com.semanta.share.model.ShareInfo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareInfoRepository extends MongoRepository<ShareInfo, String> {
}