package com.tensquare.search.service;

import com.tensquare.search.entity.Article;
import org.springframework.data.domain.Page;

/**
 * @author semiercq
 * @date 2020/11/16
 **/
public interface ArticleService {
    /**
     * 保存
     *
     * @param article article
     */
    void save(Article article);

    /**
     * 根据标题查找
     *
     * @param keywords keywords
     * @param page     page
     * @param size     size
     * @return Page
     */
    Page<Article> findByTitleLike(String keywords, int page, int size);
}
