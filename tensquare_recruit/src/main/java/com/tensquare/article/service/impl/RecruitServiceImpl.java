package com.tensquare.article.service.impl;

import com.tensquare.article.entity.Recruit;
import com.tensquare.article.repository.RecruitRepository;
import com.tensquare.article.service.RecruitService;
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
public class RecruitServiceImpl implements RecruitService {
    @Autowired
    private RecruitRepository recruitRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Recruit> recommend() {
        return recruitRepository.findTop6ByStateOrderByCreateTimeDesc("2");
    }

    @Override
    public List<Recruit> newList() {
        return recruitRepository.findTop6ByStateNotOrderByCreateTimeDesc("0");
    }

    @Override
    public List<Recruit> findAll() {
        return recruitRepository.findAll();
    }

    @Override
    public Page<Recruit> findSearch(Map whereMap, int page, int size) {
        Specification<Recruit> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return recruitRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Recruit> findSearch(Map whereMap) {
        Specification<Recruit> specification = createSpecification(whereMap);
        return recruitRepository.findAll(specification);
    }

    @Override
    public Recruit findById(String id) {
        return recruitRepository.findById(id).get();
    }

    @Override
    public void add(Recruit recruit) {
        recruit.setId(idWorker.nextId() + "");
        recruitRepository.save(recruit);
    }

    @Override
    public void update(Recruit recruit) {
        recruitRepository.save(recruit);
    }

    @Override
    public void deleteById(String id) {
        recruitRepository.deleteById(id);
    }

    @Override
    public Specification<Recruit> createSpecification(Map searchMap) {
        return new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 职位名称
                if (searchMap.get("jobName") != null && !"".equals(searchMap.get("jobName"))) {
                    predicateList.add(cb.like(root.get("jobName").as(String.class), "%" + (String) searchMap.get("jobName") + "%"));
                }
                // 薪资范围
                if (searchMap.get("salary") != null && !"".equals(searchMap.get("salary"))) {
                    predicateList.add(cb.like(root.get("salary").as(String.class), "%" + (String) searchMap.get("salary") + "%"));
                }
                // 经验要求
                if (searchMap.get("condition") != null && !"".equals(searchMap.get("condition"))) {
                    predicateList.add(cb.like(root.get("condition").as(String.class), "%" + (String) searchMap.get("condition") + "%"));
                }
                // 学历要求
                if (searchMap.get("education") != null && !"".equals(searchMap.get("education"))) {
                    predicateList.add(cb.like(root.get("education").as(String.class), "%" + (String) searchMap.get("education") + "%"));
                }
                // 任职方式
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
                }
                // 办公地址
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                // 企业ID
                if (searchMap.get("eid") != null && !"".equals(searchMap.get("eid"))) {
                    predicateList.add(cb.like(root.get("eid").as(String.class), "%" + (String) searchMap.get("eid") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                // 网址
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
                }
                // 标签
                if (searchMap.get("label") != null && !"".equals(searchMap.get("label"))) {
                    predicateList.add(cb.like(root.get("label").as(String.class), "%" + (String) searchMap.get("label") + "%"));
                }
                // 职位描述
                if (searchMap.get("content1") != null && !"".equals(searchMap.get("content1"))) {
                    predicateList.add(cb.like(root.get("content1").as(String.class), "%" + (String) searchMap.get("content1") + "%"));
                }
                // 职位要求
                if (searchMap.get("content2") != null && !"".equals(searchMap.get("content2"))) {
                    predicateList.add(cb.like(root.get("content2").as(String.class), "%" + (String) searchMap.get("content2") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
