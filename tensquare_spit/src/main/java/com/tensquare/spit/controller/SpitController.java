package com.tensquare.spit.controller;

import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author semiercq
 * @date 2020/11/15
 **/
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Resource
    private RedisTemplate<String, Integer> redisTemplate;

    @GetMapping()
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @GetMapping("{/spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @PostMapping()
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @GetMapping("/comment/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentId(parentId, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @PutMapping("/thumbUp/{spitId}")
    public Result thumbUp(@PathVariable String spitId) {
        // 解决重复点赞问题 TODO
        String userid = "111";
        if (redisTemplate.opsForValue().get("thumbUp_" + userid) != null) {
            return new Result(false, StatusCode.ERROR, "不能重复点赞");
        }
        spitService.thumbUp(spitId);
        redisTemplate.opsForValue().set("thumbUp_" + userid, 1);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

}

