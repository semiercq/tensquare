package com.tensquare.article.service;

import com.tensquare.article.entity.Channel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface ChannelService {

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Channel> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page     page
     * @param size     siz
     * @return Page
     */
    Page<Channel> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Channel> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return Channel
     */
    Channel findById(String id);

    /**
     * 增加
     *
     * @param channel channel
     */
    void add(Channel channel);

    /**
     * 修改
     *
     * @param channel channel
     */
    public void update(Channel channel);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(String id);

    /**
     * 动态条件构建
     *
     * @param searchMap searchMap
     * @return Specification<Channel>
     */
    Specification<Channel> createSpecification(Map searchMap);
}
