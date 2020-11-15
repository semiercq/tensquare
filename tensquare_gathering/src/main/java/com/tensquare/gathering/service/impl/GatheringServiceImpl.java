package com.tensquare.gathering.service.impl;

import com.tensquare.gathering.entity.Gathering;
import com.tensquare.gathering.repository.GatheringRepository;
import com.tensquare.gathering.service.GatheringService;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
 * @date 2020/11/13
 **/
@Service
public class GatheringServiceImpl implements GatheringService {

    @Autowired
    private GatheringRepository gatheringRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Gathering> findAll() {
        return gatheringRepository.findAll();
    }

    @Override
    public Page<Gathering> findSearch(Map whereMap, int page, int size) {
        Specification<Gathering> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return gatheringRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Gathering> findSearch(Map whereMap) {
        Specification<Gathering> specification = createSpecification(whereMap);
        return gatheringRepository.findAll(specification);
    }

    @Override
    @Cacheable(value = "gathering", key = "#id")
    public Gathering findById(String id) {
        return gatheringRepository.findById(id).get();
    }

    @Override
    public void add(Gathering gathering) {
        gathering.setId(idWorker.nextId() + "");
        gatheringRepository.save(gathering);
    }

    @Override
    @CacheEvict(value = "gathering", key = "#gathering.id")
    public void update(Gathering gathering) {
        gatheringRepository.save(gathering);
    }

    @Override
    @CacheEvict(value = "gathering", key = "#id")
    public void deleteById(String id) {
        gatheringRepository.deleteById(id);
    }

    @Override
    public Specification<Gathering> createSpecification(Map searchMap) {

        return new Specification<Gathering>() {

            @Override
            public Predicate toPredicate(Root<Gathering> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 编号
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 活动名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 大会简介
                if (searchMap.get("summary") != null && !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(root.get("summary").as(String.class), "%" + (String) searchMap.get("summary") + "%"));
                }
                // 详细说明
                if (searchMap.get("detail") != null && !"".equals(searchMap.get("detail"))) {
                    predicateList.add(cb.like(root.get("detail").as(String.class), "%" + (String) searchMap.get("detail") + "%"));
                }
                // 主办方
                if (searchMap.get("sponsor") != null && !"".equals(searchMap.get("sponsor"))) {
                    predicateList.add(cb.like(root.get("sponsor").as(String.class), "%" + (String) searchMap.get("sponsor") + "%"));
                }
                // 活动图片
                if (searchMap.get("image") != null && !"".equals(searchMap.get("image"))) {
                    predicateList.add(cb.like(root.get("image").as(String.class), "%" + (String) searchMap.get("image") + "%"));
                }
                // 举办地点
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                // 是否可见
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                // 城市
                if (searchMap.get("city") != null && !"".equals(searchMap.get("city"))) {
                    predicateList.add(cb.like(root.get("city").as(String.class), "%" + (String) searchMap.get("city") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
