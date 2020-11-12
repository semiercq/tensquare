package com.tensquare.article.service;

import com.tensquare.article.entity.Recruit;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/12
 **/
public interface RecruitService {

    /**
     * 推荐列表
     *
     * @return list
     */
    List<Recruit> recommend();

    /**
     * 新发布列表
     *
     * @return list
     */
    List<Recruit> newList();

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Recruit> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page PAGE
     * @param size size
     * @return Page
     */
    Page<Recruit> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return LIST
     */
    List<Recruit> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return Recruit
     */
    Recruit findById(String id);

    /**
     * 增加
     *
     * @param recruit recruit
     */
    void add(Recruit recruit);

    /**
     * 修改
     *
     * @param recruit recruit
     */
    void update(Recruit recruit);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(String id);

    /**
     * 动态条件构建
     *
     * @param searchMap whereMap
     * @return Specification<Recruit>
     */
    Specification<Recruit> createSpecification(Map searchMap);
}
