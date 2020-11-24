package com.tensquare.user.service;

import com.tensquare.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
public interface UserService {

    /**
     * 根据手机号和密码查询用户
     *
     * @param mobile mobile
     * @param password password
     * @return User
     */
    User findByMobileAndPassword(String mobile, String password);

    /**
     * 添加
     *
     * @param user user
     */
    void add(User user);

    /**
     * 发送短信
     *
     * @param mobile mobile
     */
    void sendSms(String mobile);

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<User> findAll();

    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page page
     * @param size size
     * @return Page
     */
    Page<User> findSearch(Map whereMap, int page, int size);

    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<User> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return User
     */
    User findById(String id);

    /**
     * 修改
     *
     * @param user user
     */
    void update(User user);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(String id);

    /**
     * 动态条件构建
     *
     * @param searchMap searchMap
     * @return Specification<User>
     */
    Specification<User> createSpecification(Map searchMap);

    /**
     * 登录
     * @param mobile mobile
     * @param password password
     * @return User
     */
    User login(String mobile, String password);

    /**
     *
     * @param x
     * @param userId
     * @param friendId
     */
    void updateFansCountAndFollowCount(int x, String userId, String friendId);
}
