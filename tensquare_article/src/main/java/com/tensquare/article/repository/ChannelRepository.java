package com.tensquare.article.repository;

import com.tensquare.article.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface ChannelRepository extends JpaRepository<Channel,String>, JpaSpecificationExecutor<Channel> {

}
