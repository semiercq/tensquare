package com.tensquare.article.service.impl;

import com.tensquare.article.service.ColumnService;
import com.tensquare.article.entity.Column;
import com.tensquare.article.repository.ColumnRepository;
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
 * @date 2020/11/13
 **/
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Column> findAll() {
        return columnRepository.findAll();
    }

    @Override
    public Page<Column> findSearch(Map whereMap, int page, int size) {
        Specification<Column> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return columnRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Column> findSearch(Map whereMap) {
        Specification<Column> specification = createSpecification(whereMap);
        return columnRepository.findAll(specification);
    }

    @Override
    public Column findById(String id) {
        return columnRepository.findById(id).get();
    }

    @Override
    public void add(Column column) {
        column.setId(idWorker.nextId() + "");
        columnRepository.save(column);
    }

    @Override
    public void update(Column column) {
        columnRepository.save(column);
    }

    @Override
    public void deleteById(String id) {
        columnRepository.deleteById(id);
    }

    @Override
    public Specification<Column> createSpecification(Map searchMap) {
        return new Specification<Column>() {
            @Override
            public Predicate toPredicate(Root<Column> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 专栏名称
                if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%" + (String) searchMap.get("name") + "%"));
                }
                // 专栏简介
                if (searchMap.get("summary") != null && !"".equals(searchMap.get("summary"))) {
                    predicateList.add(cb.like(root.get("summary").as(String.class), "%" + (String) searchMap.get("summary") + "%"));
                }
                // 用户ID
                if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + (String) searchMap.get("userId") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
