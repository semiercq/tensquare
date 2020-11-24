package com.tensquare.qa.controller;

import com.tensquare.qa.client.BaseClient;
import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/11
 **/
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private BaseClient baseClient;


    /**
     * 根据ID查询
     * @param labelId ID
     * @return Result
     */
    @GetMapping("/label/{labelId}")
    public Result findById(@PathVariable String labelId){
        return baseClient.findById(labelId);
    }

    @GetMapping("/newList/{labelId}/{page}/{size}")
    public Result newList(@PathVariable String labelId,
                          @PathVariable int page,
                          @PathVariable int size) {
        Page<Problem> pageData = problemService.newList(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));

    }

    @GetMapping("/hotList/{labelId}/{page}/{size}")
    public Result hotList(@PathVariable String labelId,
                          @PathVariable int page,
                          @PathVariable int size) {
        Page<Problem> pageData = problemService.hotList(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));

    }

    @GetMapping("/waitList/{labelId}/{page}/{size}")
    public Result waitList(@PathVariable String labelId,
                           @PathVariable int page,
                           @PathVariable int size) {
        Page<Problem> pageData = problemService.waitList(labelId, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Problem>(pageData.getTotalElements(), pageData.getContent()));

    }

    /**
     * 查询全部数据
     *
     * @return Result
     */
    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findAll());
    }

    // TODO
//    /**
//     * 根据ID查询
//     *
//     * @param labelId ID
//     * @return Result
//     */
//    @GetMapping("/label/{labelId}")
//    public Result findById(@PathVariable String labelId) {
//        return labelClient.findById(labelId);
//    }

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
        Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap searchMap
     * @return Result
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param problem problem
     */
    @PostMapping()
    public Result add(@RequestBody Problem problem) {
        problemService.add(problem);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param problem problem
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.update(problem);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        problemService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
