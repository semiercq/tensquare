package com.tensquare.gathering.repository;

import com.tensquare.gathering.entity.Gathering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
public interface GatheringRepository extends JpaRepository<Gathering,String>, JpaSpecificationExecutor<Gathering> {

}
