package com.tensquare.search.repository;

import com.tensquare.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author semiercq
 * @date 2020/11/16
 **/
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    /**
     * 分页查询
     *
     * @param title title
     * @param content content
     * @param pageable pageable
     * @return Page
     */
    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
