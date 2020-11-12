package com.tensquare.qa.service.impl;

import com.tensquare.qa.entity.Problem;
import com.tensquare.qa.repository.ProblemRepository;
import com.tensquare.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author semiercq
 * @date 2020/11/11
 **/
@Service
@Transactional
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Page<Problem> newList(String labelId, int page, int rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return problemRepository.newList(labelId, pageable);
    }

    @Override
    public Page<Problem> hotList(String labelId, int page, int rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return problemRepository.hotList(labelId, pageable);
    }

    @Override
    public Page<Problem> waitList(String labelId, int page, int rows) {
        Pageable pageable = PageRequest.of(page - 1, rows);
        return problemRepository.waitList(labelId, pageable);
    }

    @Override
    public List<Problem> findAll() {
        return problemRepository.findAll();
    }

    @Override
    public Page<Problem> findSearch(Map whereMap, int page, int size) {
        Specification<Problem> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return problemRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Problem> findSearch(Map whereMap) {
        Specification<Problem> specification = createSpecification(whereMap);
        return problemRepository.findAll(specification);
    }

    @Override
    public Problem findById(String id) {
        return problemRepository.findById(id).get();
    }

    @Override
    public void add(Problem problem) {
        problem.setId(idWorker.nextId() + "");
        problemRepository.save(problem);
    }

    @Override
    public void update(Problem problem) {
        problemRepository.save(problem);
    }

    @Override
    public void deleteById(String id) {
        problemRepository.deleteById(id);
    }

    @Override
    public Specification<Problem> createSpecification(Map searchMap) {
        return new Specification<Problem>() {
            /**
             * 条件查询
             * @param root 根对象，也就是要把对象封装到哪个对象中。where 类名=label.getId
             * @param query 封装的都是查询的关键字，比如group by order by 等
             * @param cb 用来封装条件对象
             * @return 如果返回null表示不需要任何条件
             */
            @Override
            public Predicate toPredicate(Root<Problem> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 标题
                if (searchMap.get("title") != null && !"".equals(searchMap.get("title"))) {
                    predicateList.add(cb.like(root.get("title").as(String.class), "%" + (String) searchMap.get("title") + "%"));
                }
                // 内容
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
                // 用户ID
                if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + (String) searchMap.get("userId") + "%"));
                }
                // 昵称
                if (searchMap.get("nickName") != null && !"".equals(searchMap.get("nickName"))) {
                    predicateList.add(cb.like(root.get("nickName").as(String.class), "%" + (String) searchMap.get("nickName") + "%"));
                }
                // 是否解决
                if (searchMap.get("solve") != null && !"".equals(searchMap.get("solve"))) {
                    predicateList.add(cb.like(root.get("solve").as(String.class), "%" + (String) searchMap.get("solve") + "%"));
                }
                // 回复人昵称
                if (searchMap.get("replyName") != null && !"".equals(searchMap.get("replyName"))) {
                    predicateList.add(cb.like(root.get("replyName").as(String.class), "%" + (String) searchMap.get("replyName") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }
}
