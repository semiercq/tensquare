package com.tensquare.friend.repository;

import com.tensquare.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author semiercq
 * @date 2020/11/23
 **/

public interface FriendRepository extends JpaRepository<Friend, String> {
    Friend findByUserIdAndFriendId(String userId, String friendId);

    @Modifying
    @Query(value = "UPDATE tb_friend SET is_like=? WHERE user_id = ? AND friend_id = ?", nativeQuery = true)
    void updateIsLike(String isLike, String userId, String friendId);

    @Modifying
    @Query(value = "delete FROM tb_friend WHERE user_id = ? AND friend_id = ?", nativeQuery = true)
    void deleteFriend(String userId, String friendId);
}

