package com.tensquare.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author semiercq
 * @date 2020/11/23
 **/
@FeignClient("tensquare-user")
public interface UserClient {

    /**
     * 增加粉丝数
     *
     * @param userId userId
     * @param x      x
     */
    @PostMapping("/user/incFans/{userId}/{x}")
    void incFansCount(@PathVariable("userId") String userId, @PathVariable("x") int x);

    /**
     * 增加关注数
     *
     * @param userId userId
     * @param x      x
     */
    @PostMapping("/user/incFollow/{userId}/{x}")
    void incFollowCount(@PathVariable("userId") String userId, @PathVariable("x") int x);
}

