package com.tensquare.search.service.impl;

import com.tensquare.search.entity.Article;
import com.tensquare.search.repository.ArticleRepository;
import com.tensquare.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author semiercq
 * @date 2020/11/16
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

//    @Autowired
//    private IdWorker idWorker;

    @Override
    public void save(Article article) {
        //article.setId(idWorker.nextId()+"");
        articleRepository.save(article);
    }

    @Override
    public Page<Article> findByTitleLike(String keywords, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleRepository.findByTitleOrContentLike(keywords, keywords, pageable);
    }
}
