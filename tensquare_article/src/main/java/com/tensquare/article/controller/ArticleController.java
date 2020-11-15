package com.tensquare.article.controller;

import com.tensquare.article.entity.Article;
import com.tensquare.article.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PutMapping("/examine/{articleId}")
    public Result examine(@PathVariable String articleId) {
        articleService.updateState(articleId);
        return new Result(true, StatusCode.OK, "审核成功");
    }

    @PutMapping("/thumbUp/{articleId}")
    public Result thumbUp(@PathVariable String articleId) {
        articleService.addThumbUp(articleId);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    /**
     * 查询全部数据
     * @return Result
     */
    @GetMapping()
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return Result
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findById(id));
    }

    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Article> pageList = articleService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Article>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap searchMap
     * @return Result
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",articleService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param article Result
     */
    @PostMapping()
    public Result add(@RequestBody Article article  ){
        articleService.add(article);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param article Result
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Article article, @PathVariable String id ){
        article.setId(id);
        articleService.update(article);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id ){
        articleService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
