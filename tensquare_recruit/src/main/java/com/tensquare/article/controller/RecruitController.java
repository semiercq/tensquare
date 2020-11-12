package com.tensquare.article.controller;

import com.tensquare.article.entity.Recruit;
import com.tensquare.article.service.RecruitService;
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
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @GetMapping("/search/recommend")
    public Result recommend() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.recommend());
    }

    @GetMapping("/search/newList")
    public Result newList() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.newList());
    }

    /**
     * 查询全部数据
     *
     * @return Result
     */
    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return Result
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(id));
    }

    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Recruit> pageList = recruitService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Recruit>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap searchMap
     * @return Result
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", recruitService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param recruit recruit
     */
    @PostMapping()
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param recruit recruit
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Recruit recruit, @PathVariable String id) {
        recruit.setId(id);
        recruitService.update(recruit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}

