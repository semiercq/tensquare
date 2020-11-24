package com.tensquare.friend.repository;

import com.tensquare.friend.entity.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author semiercq
 * @date 2020/11/23
 **/
@Component
public interface NoFriendRepository extends JpaRepository<NoFriend, String> {
    NoFriend findByUserIdAndFriendId(String userId, String friendId);
}
