package com.tensquare.friend.service.impl;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.entity.Friend;
import com.tensquare.friend.entity.NoFriend;
import com.tensquare.friend.repository.FriendRepository;
import com.tensquare.friend.repository.NoFriendRepository;
import com.tensquare.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author semiercq
 * @date 2020/11/23
 **/
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private NoFriendRepository noFriendRepository;

    @Autowired
    private UserClient userClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addFriend(String userId, String friendId) {

        //先判断userid到friendId是否有数据，有就是重复添加好友，返回0
        Friend friend = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friend != null) {
            return 0;
        }
        //直接添加好友，让好友表中userid到friendId方向的type为0
        friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friend.setIsLike("0");
        friendRepository.save(friend);
        //判断从friendId到userid是否有数据，如果有，把双方的状态都改为1
        if (friendRepository.findByUserIdAndFriendId(friendId, userId) != null) {
            //把双方的isLike都改成1
            friendRepository.updateIsLike("1", userId, friendId);
            friendRepository.updateIsLike("1", friendId, userId);
        }
        return 1;
    }


    @Override
    public void deleteFriend(String userId, String friendId) {
        //删除还有表中userid到friendId这条数据
        friendRepository.deleteFriend(userId, friendId);
        //更新friendId到userid的isLike为0
        friendRepository.updateIsLike("0", friendId, userId);
        //非好友表中添加数据
        NoFriend nofriend = new NoFriend();
        nofriend.setUserId(userId);
        nofriend.setFriendId(friendId);
        noFriendRepository.save(nofriend);

    }

    @Override
    public int addNoFriend(String userId, String friendId) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendRepository.findByUserIdAndFriendId(userId, friendId);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserId(userId);
        noFriend.setFriendId(friendId);
        noFriendRepository.save(noFriend);
        return 1;
    }
}
