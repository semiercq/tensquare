package com.tensquare.recruit.service.impl;

import com.tensquare.recruit.entity.Enterprise;
import com.tensquare.recruit.repository.EnterpriseRepository;
import com.tensquare.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/12
 **/
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Enterprise> hotList(String isHot) {
        return enterpriseRepository.findByIsHot(isHot);
    }

    @Override
    public List<Enterprise> findAll() {
        return enterpriseRepository.findAll();
    }

    @Override
    public Page<Enterprise> findSearch(Map whereMap, int page, int size) {
        Specification<Enterprise> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return enterpriseRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Enterprise> findSearch(Map whereMap) {
        Specification<Enterprise> specification = createSpecification(whereMap);
        return enterpriseRepository.findAll(specification);
    }

    @Override
    public Enterprise findById(String id) {
        return enterpriseRepository.findById(id).get();
    }

    @Override
    public void add(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId() + "");
        enterpriseRepository.save(enterprise);
    }

    @Override
    public void update(Enterprise enterprise) {
        enterpriseRepository.save(enterprise);
    }

    @Override
    public void deleteById(String id) {
        enterpriseRepository.deleteById(id);
    }

    @Override
    public Specification<Enterprise> createSpecification(Map searchMap) {
        return new Specification<Enterprise>() {
            @Override
            public Predicate toPredicate(Root<Enterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 企业名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 企业简介
                if (searchMap.get("summary") != null && !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(root.get("summary").as(String.class), "%" + (String) searchMap.get("summary") + "%"));
                }
                // 企业地址
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                // 标签列表
                if (searchMap.get("labels") != null && !"".equals(searchMap.get("labels"))) {
                    predicateList.add(cb.like(root.get("labels").as(String.class), "%" + (String) searchMap.get("labels") + "%"));
                }
                // 坐标
                if (searchMap.get("coordinate") != null && !"".equals(searchMap.get("coordinate"))) {
                    predicateList.add(cb.like(root.get("coordinate").as(String.class), "%" + (String) searchMap.get("coordinate") + "%"));
                }
                // 是否热门
                if (searchMap.get("isHot") != null && !"".equals(searchMap.get("isHot"))) {
                    predicateList.add(cb.like(root.get("isHot").as(String.class), "%" + (String) searchMap.get("isHot") + "%"));
                }
                // LOGO
                if (searchMap.get("logo") != null && !"".equals(searchMap.get("logo"))) {
                    predicateList.add(cb.like(root.get("logo").as(String.class), "%" + (String) searchMap.get("logo") + "%"));
                }
                // URL
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }
}
