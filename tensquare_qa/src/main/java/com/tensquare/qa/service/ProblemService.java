package com.tensquare.qa.service;

import com.tensquare.qa.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/11
 **/
public interface ProblemService {

    /**
     * 查询最新问题列表
     *
     * @param labelId labelId
     * @param page page
     * @param rows rows
     * @return Page
     */
    Page<Problem> newList(String labelId, int page, int rows);

    /**
     * 查询热门问题列表
     *
     * @param labelId labelId
     * @param page page
     * @param rows rows
     * @return Page
     */
    Page<Problem> hotList(String labelId, int page, int rows);

    /**
     * 查询等待回答问题列表
     *
     * @param labelId labelId
     * @param page page
     * @param rows rows
     * @return Page
     */
    Page<Problem> waitList(String labelId, int page, int rows);

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Problem> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page page
     * @param size size
     * @return Page
     */
    Page<Problem> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Problem> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return Problem
     */
    Problem findById(String id);

    /**
     * 增加
     *
     * @param problem problem
     */
    void add(Problem problem);

    /**
     * 修改
     *
     * @param problem problem
     */
    void update(Problem problem);

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
     * @return
     */
    Specification<Problem> createSpecification(Map searchMap);
}
