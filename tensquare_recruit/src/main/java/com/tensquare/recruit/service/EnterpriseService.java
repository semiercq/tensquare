package com.tensquare.recruit.service;

import com.tensquare.recruit.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/12
 **/
public interface EnterpriseService {

    /**
     * 查询热门企业列表
     *
     * @param isHot isHot
     * @return list
     */
    List<Enterprise> hotList(String isHot);

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Enterprise> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page page
     * @param size size
     * @return Page
     */
    Page<Enterprise> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Enterprise> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return Enterprise
     */
    Enterprise findById(String id);

    /**
     * 增加
     *
     * @param enterprise enterprise
     */
    void add(Enterprise enterprise);

    /**
     * 修改
     *
     * @param enterprise enterprise
     */
    void update(Enterprise enterprise);

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
     * @return Specification<Enterprise>
     */
    Specification<Enterprise> createSpecification(Map searchMap);
}
