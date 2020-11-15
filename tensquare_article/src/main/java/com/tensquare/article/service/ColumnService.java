package com.tensquare.article.service;

import com.tensquare.article.entity.Column;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface ColumnService {

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Column> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page     page
     * @param size     size
     * @return Page
     */
    Page<Column> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Column> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return Column
     */
    Column findById(String id);

    /**
     * 增加
     *
     * @param column column
     */
    void add(Column column);

    /**
     * 修改
     *
     * @param column column
     */
    void update(Column column);

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
     * @return Specification<Column>
     */
    Specification<Column> createSpecification(Map searchMap);

}
