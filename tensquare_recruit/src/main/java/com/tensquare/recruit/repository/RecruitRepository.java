package com.tensquare.recruit.repository;

import com.tensquare.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author semiercq
 * @date 2020/11/12
 **/
public interface RecruitRepository extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {
    /**
     * where state = "1" order by createTime
     *
     * @param state state
     * @return list
     */
    List<Recruit> findTop6ByStateOrderByCreateTimeDesc(String state);

    /**
     * where state != ? order by createTime
     *
     * @param state state
     * @return list
     */
    List<Recruit> findTop6ByStateNotOrderByCreateTimeDesc(String state);
}
