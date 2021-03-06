package com.tensquare.article.controller;

import com.tensquare.article.entity.Channel;
import com.tensquare.article.service.ChannelService;
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
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 查询全部数据
     * @return Result
     */
    @GetMapping()
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",channelService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return Result
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",channelService.findById(id));
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
        Page<Channel> pageList = channelService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Channel>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap searchMap
     * @return Result
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",channelService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param channel channel
     */
    @PostMapping()
    public Result add(@RequestBody Channel channel  ){
        channelService.add(channel);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param channel channel
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Channel channel, @PathVariable String id ){
        channel.setId(id);
        channelService.update(channel);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id ){
        channelService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

}

