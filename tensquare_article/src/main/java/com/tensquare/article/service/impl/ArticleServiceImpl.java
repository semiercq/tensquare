package com.tensquare.article.service.impl;

import com.tensquare.article.service.ArticleService;
import com.tensquare.article.entity.Article;
import com.tensquare.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IdWorker idWorker;

    @Resource
    private RedisTemplate<String, Article> redisTemplate;

    @Override
    public void updateState(String id) {
        articleRepository.updateState(id);
    }

    @Override
    public void addThumbUp(String id) {
        articleRepository.addThumbUp(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> findSearch(Map whereMap, int page, int size) {
        Specification<Article> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Article> findSearch(Map whereMap) {
        Specification<Article> specification = createSpecification(whereMap);
        return articleRepository.findAll(specification);
    }

    @Override
    public Article findById(String id) {
        Article article = redisTemplate.opsForValue().get("article_" + id);
        if (article == null) {
            article = articleRepository.findById(id).get();
            redisTemplate.opsForValue().set("article_" + id, article, 60 * 10, TimeUnit.SECONDS);
        }
        return article;
    }

    @Override
    public void add(Article article) {
        article.setId(idWorker.nextId() + "");
        articleRepository.save(article);
    }

    @Override
    public void update(Article article) {
        // 从缓存中删除
        redisTemplate.delete("article_" + article.getId());
        articleRepository.save(article);
    }

    @Override
    public void deleteById(String id) {
        redisTemplate.delete("article_" + id);
        articleRepository.deleteById(id);
    }

    @Override
    public Specification<Article> createSpecification(Map searchMap) {

        return new Specification<Article>() {

            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 专栏ID
                if (searchMap.get("columnId") != null && !"".equals(searchMap.get("columnId"))) {
                    predicateList.add(cb.like(root.get("columnId").as(String.class), "%" + (String) searchMap.get("columnId") + "%"));
                }
                // 用户ID
                if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + (String) searchMap.get("userId") + "%"));
                }
                // 标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                // 文章正文
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
                // 文章封面
                if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                    predicateList.add(cb.like(root.get("image").as(String.class), "%" + (String) searchMap.get("image") + "%"));
                }
                // 是否公开
                if (searchMap.get("isPublic") != null && !"".equals(searchMap.get("isPublic"))) {
                    predicateList.add(cb.like(root.get("isPublic").as(String.class), "%" + (String) searchMap.get("isPublic") + "%"));
                }
                // 是否置顶
                if (searchMap.get("isTop") != null && !"".equals(searchMap.get("isTop"))) {
                    predicateList.add(cb.like(root.get("isTop").as(String.class), "%" + (String) searchMap.get("isTop") + "%"));
                }
                // 审核状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                // 所属频道
                if (searchMap.get("channelId") != null && !"".equals(searchMap.get("channelId"))) {
                    predicateList.add(cb.like(root.get("channelId").as(String.class), "%" + (String) searchMap.get("channelId") + "%"));
                }
                // URL
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
                }
                // 类型
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
