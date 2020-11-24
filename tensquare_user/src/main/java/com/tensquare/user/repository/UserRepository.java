package com.tensquare.user.repository;

import com.tensquare.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据手机号查找
     *
     * @param mobile 手机号
     * @return User
     */
    User findByMobile(String mobile);


    @Modifying
    @Query(value = "update tb_user set fans_count=fans_count+? where id=?", nativeQuery = true)
    public void updateFansCount(int x, String friendId);

    @Modifying
    @Query(value = "update tb_user set follow_count=follow_count+? where id=?", nativeQuery = true)
    public void updateFollowCount(int x, String userId);

}
