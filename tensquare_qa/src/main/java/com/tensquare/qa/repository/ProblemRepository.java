package com.tensquare.qa.repository;

import com.tensquare.qa.entity.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author semiercq
 * @date 2020/11/11
 **/
public interface ProblemRepository extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    /**
     * 查询最新问题列表
     * nativeQuery = true 则前面value值可以是sql语句。不指定默认是HQL
     *
     * @param labelId  labelId
     * @param pageable pageable
     * @return page
     */
    @Query(value = "select * from tb_problem, tb_pl where id = problem_id and label_id=? order by reply_time desc ", nativeQuery = true)
    Page<Problem> newList(String labelId, Pageable pageable);

    /**
     * 查询最热问题列表
     *
     * @param labelId  labelId
     * @param pageable pageable
     * @return page
     */
    @Query(value = "select * from tb_problem, tb_pl where id = problem_id and label_id=? order by reply desc ", nativeQuery = true)
    Page<Problem> hotList(String labelId, Pageable pageable);

    /**
     * 查询待回答问题列表
     *
     * @param labelId  labelId
     * @param pageable pageable
     * @return page
     */
    @Query(value = "select * from tb_problem, tb_pl where id = problem_id and label_id=? and reply=0 order by create_time desc ", nativeQuery = true)
    Page<Problem> waitList(String labelId, Pageable pageable);

}
