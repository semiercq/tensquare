package com.tensquare.qa.repository;

import com.tensquare.qa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author semiercq
 * @date 2020/11/11
 **/
public interface ReplyRepository extends JpaRepository<Reply, String>, JpaSpecificationExecutor<Reply> {
}
