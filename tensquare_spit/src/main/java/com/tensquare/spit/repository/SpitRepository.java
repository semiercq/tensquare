package com.tensquare.spit.repository;

import com.tensquare.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author semiercq
 * @date 2020/11/15
 **/
public interface SpitRepository extends MongoRepository<Spit, String> {

    /**
     * 分页查找
     *
     * @param parentId parentId
     * @param pageable pageable
     * @return Page
     */
    Page<Spit> findByParentId(String parentId, Pageable pageable);

}

