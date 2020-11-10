package com.tensquare.base.controller;

import com.tensquare.base.entity.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author semiercq
 * @date 2020/11/09
 **/
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查找全部列表
     *
     * @return Result
     */
    @GetMapping("")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 根据ID查询标签
     *
     * @param labelId labelId
     * @return Result
     */
    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId) {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    /**
     * 保存标签
     *
     * @param label label
     * @return Result
     */
    @PostMapping()
    public Result save(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改标签
     *
     * @param labelId labelId
     * @param label   label
     * @return Result
     */
    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        // TODO
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除标签
     *
     * @param labelId labelId
     * @return Result
     */
    @DeleteMapping("/{labelId}")
    public Result deleteById(@PathVariable("labelId") String labelId) {
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 条件查询
     *
     * @param label label
     * @return Result<list>
     */
    @PostMapping("/search")
    public Result search(@RequestBody Label label) {
        List<Label> list = labelService.find(label);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> pageData = labelService.pageQuery(label, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(pageData.getTotalElements(), pageData.getContent()));
    }




}
