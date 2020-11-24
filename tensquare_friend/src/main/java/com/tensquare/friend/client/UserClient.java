package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author semiercq
 * @date 2020/11/23
 **/
@FeignClient("tensquare-user")
public interface UserClient {

    @RequestMapping(value = "/user/{userId}/{friendId}/{x}", method = RequestMethod.PUT)
    void updateFansCountAndFollowCount(@PathVariable("userId") String userId, @PathVariable("friendId") String friendId, @PathVariable("x") int x);

}

