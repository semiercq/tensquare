package com.tensquare.user.repository;

import com.tensquare.user.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
public interface AdminRepository extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

    /**
     * 根据登录名查找
     *
     * @param loginName 登录名
     * @return Admin
     */
    public Admin findByLoginName(String loginName);
}
