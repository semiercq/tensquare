package com.tensquare.user.controller;

import com.tensquare.user.entity.User;
import com.tensquare.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/{userId}/{friendId}/{x}", method = RequestMethod.PUT)
    public void updateFansCountAndFollowCount(@PathVariable String userId, @PathVariable String friendId, @PathVariable int x){
        userService.updateFansCountAndFollowCount(x, userId, friendId);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user){
        user = userService.login(user.getMobile(), user.getPassword());
        if(user==null){
            return new Result(false, StatusCode.LOGINERROR, "登录失败");
        }
        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("roles", "user");
        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    @RequestMapping(value = "/sendSms/{mobile}", method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return new Result(true, StatusCode.OK, "发送成功");
    }

    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result register(@PathVariable String code, @RequestBody User user){
        //得到缓存中的验证码
        String checkCodeRedis = (String) redisTemplate.opsForValue().get("checkCode_" + user.getMobile());
        if(checkCodeRedis.isEmpty()){
            return new Result(false, StatusCode.ERROR, "请先获取手机验证码");
        }
        if(!checkCodeRedis.equals(code)){
            return new Result(false, StatusCode.ERROR, "请输入正确的验证码");
        }
        userService.add(user);
        return new Result(true, StatusCode.OK, "注册成功");
    }

    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
    }

    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
    }

    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
    }

    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }

    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody User user  ){
        userService.add(user);
        return new Result(true,StatusCode.OK,"增加成功");
    }


    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id ){
        user.setId(id);
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
