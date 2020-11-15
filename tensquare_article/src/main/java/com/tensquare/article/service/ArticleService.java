package com.tensquare.article.service;

import com.tensquare.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface ArticleService {

    /**
     * 更新状态
     *
     * @param id id
     */
    void updateState(String id);

    /**
     * 点赞
     *
     * @param id id
     */
    void addThumbUp(String id);

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Article> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page     page
     * @param size     size
     * @return Page
     */
    Page<Article> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Article> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     * 使用redis缓存
     *
     * @param id id
     * @return Article
     */
    Article findById(String id);

    /**
     * 增加
     *
     * @param article article
     */
    void add(Article article);

    /**
     * 修改
     *
     * @param article article
     */
    void update(Article article);

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
     * @return Specification<Article>
     */
    Specification<Article> createSpecification(Map searchMap);
}
