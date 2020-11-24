package com.tensquare.friend.service;

/**
 * @author semiercq
 * @date 2020/11/23
 **/
public interface FriendService {

    /**
     * 添加好友
     *
     * @param userId   userId
     * @param friendId friendId
     * @return int
     */
    //    @Transactional(rollbackFor = Exception.class)
    int addFriend(String userId, String friendId);

    /**
     * 删除好友
     *
     * @param userId   userId
     * @param friendId friendId
     */
    void deleteFriend(String userId, String friendId);

    /**
     * 添加非好友
     *
     * @param userId   userId
     * @param friendId friendId
     * @return int
     */
    int addNoFriend(String userId, String friendId);
}
