package com.tensquare.gathering.service;

import com.tensquare.gathering.entity.Gathering;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface GatheringService {

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Gathering> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page     page
     * @param size     size
     * @return Page
     */
    Page<Gathering> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Gathering> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id ID
     * @return Gathering
     */
    Gathering findById(String id);

    /**
     * 增加
     *
     * @param gathering gathering
     */
    void add(Gathering gathering);

    /**
     * 修改
     *
     * @param gathering gathering
     */
    void update(Gathering gathering);

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
     * @return Specification<Gathering>
     */
    Specification<Gathering> createSpecification(Map searchMap);
}
