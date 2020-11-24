package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author semiercq
 * @date 2020/11/23
 **/
@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;

    /**
     * 删除好友
     *
     * @param friendId friendId
     * @return Result
     */
    @DeleteMapping("/{friendId}")
    public Result remove(@PathVariable String friendId) {
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            //说明当前用户没有user角色
            return new Result(false, StatusCode.LOGINERROR, "权限不足");
        }
        //得到当前登录的用户id
        String userid = claims.getId();
        friendService.deleteFriend(userid, friendId);
        userClient.updateFansCountAndFollowCount(userid, friendId, -1);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @PutMapping("/like/{friendId}/{type}")
    public Result addFriend(@PathVariable String friendId, @PathVariable String type) {
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (claims == null) {
            //说明当前用户没有user角色
            return new Result(false, StatusCode.LOGINERROR, "权限不足");
        }
        //得到当前登录的用户id
        String userId = claims.getId();
        System.out.println(type + "!!!!" + userId + "@@@@" + friendId);
        //判断是添加好友还是添加非好友
        if (type != null) {
            if (type.equals("1")) {
                //添加好友
                int flag = friendService.addFriend(userId, friendId);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友");
                }
                if (flag == 1) {
                    userClient.updateFansCountAndFollowCount(userId, friendId, 1);
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            } else if (type.equals("2")) {
                //添加非好友
                int flag = friendService.addNoFriend(userId, friendId);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加非好友");
                }
                if (flag == 1) {
                    return new Result(true, StatusCode.OK, "添加成功");
                }
            }
        }
        return new Result(false, StatusCode.ERROR, "参数异常");
    }
}

