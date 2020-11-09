package com.tensquare.base.repository;

import com.tensquare.base.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author semiercq
 * @date 2020/11/09
 **/
public interface LabelRepository extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

}
