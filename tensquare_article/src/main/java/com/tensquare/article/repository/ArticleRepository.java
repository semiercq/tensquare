package com.tensquare.article.repository;

import com.tensquare.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface ArticleRepository extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {

    /**
     * 修改文章状态
     *
     * @param id id
     */
    @Modifying
    @Query(value = "update tb_article set state=1 where id = ?", nativeQuery = true)
    void updateState(String id);

    /**
     * 文章点赞
     *
     * @param id id
     */
    @Modifying
    @Query(value = "update tb_article set thumbup = thumbup+1 where id=?", nativeQuery = true)
    void addThumbUp(String id);
}
