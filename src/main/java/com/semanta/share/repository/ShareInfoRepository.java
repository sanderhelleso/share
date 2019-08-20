package com.semanta.share.repository;

import com.semanta.share.model.ShareInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareInfoRepository extends CrudRepository<ShareInfo, Long> {
}