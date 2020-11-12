package com.tensquare.qa.service;

import com.tensquare.qa.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/11
 **/
public interface ReplyService {
    /**
     * 查询全部列表
     * @return list
     */
    List<Reply> findAll();

    /**
     * 条件查询+分页
     * @param whereMap whereMap
     * @param page age
     * @param size size
     * @return Page
     */
    Page<Reply> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     * @param whereMap whereMap
     * @return list
     */
    List<Reply> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     * @param id id
     * @return Reply
     */
    Reply findById(String id);

    /**
     * 增加
     * @param reply reply
     */
    void add(Reply reply);

    /**
     * 修改
     * @param reply reply
     */
    void update(Reply reply);

    /**
     * 删除
     * @param id id
     */
    void deleteById(String id);

    /**
     * 动态条件构建
     * @param searchMap searchMap
     * @return Specification<Reply>
     */
    Specification<Reply> createSpecification(Map searchMap);
}
