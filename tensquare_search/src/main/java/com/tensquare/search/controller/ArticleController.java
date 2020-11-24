package com.tensquare.search.controller;

import com.tensquare.search.entity.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author semiercq
 * @date 2020/11/16
 **/
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article) {
        articleService.save(article);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keywords, @PathVariable int page, @PathVariable int size) {
        Page<Article> articlePage = articleService.findByTitleLike(keywords, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Article>(articlePage.getTotalElements(), articlePage.getContent()));
    }
}
