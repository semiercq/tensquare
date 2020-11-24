package com.tensquare.user.service;

import com.tensquare.user.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
public interface AdminService {

    /**
     * 根据登录名和密码查询
     */
    Admin findByLoginNameAndPassword(String loginName, String password);

    /**
     * 查询全部列表
     *
     * @return list
     */
    List<Admin> findAll();


    /**
     * 条件查询+分页
     *
     * @param whereMap whereMap
     * @param page     page
     * @param size     size
     * @return Page
     */
    Page<Admin> findSearch(Map whereMap, int page, int size);


    /**
     * 条件查询
     *
     * @param whereMap whereMap
     * @return list
     */
    List<Admin> findSearch(Map whereMap);

    /**
     * 根据ID查询实体
     *
     * @param id id
     * @return Admin
     */
    Admin findById(String id);

    /**
     * 增加
     *
     * @param admin admin
     */
    void add(Admin admin);

    /**
     * 修改
     *
     * @param admin admin
     */
    void update(Admin admin);


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
     * @return Specification<Admin>
     */
    Specification<Admin> createSpecification(Map searchMap);

}
