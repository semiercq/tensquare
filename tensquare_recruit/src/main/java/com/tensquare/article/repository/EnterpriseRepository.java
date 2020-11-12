package com.tensquare.article.repository;

import com.tensquare.article.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author semiercq
 * @date 2020/11/12
 **/
public interface EnterpriseRepository extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
    /**
     * 查询热门问题
     *
     * @param isHot isHot
     * @return list
     */
    List<Enterprise> findByIsHot(String isHot);
}