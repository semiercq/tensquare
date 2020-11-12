package com.tensquare.qa.service.impl;

import com.tensquare.qa.entity.Reply;
import com.tensquare.qa.repository.ReplyRepository;
import com.tensquare.qa.service.ReplyService;
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
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Reply> findAll() {
        return replyRepository.findAll();
    }

    @Override
    public Page<Reply> findSearch(Map whereMap, int page, int size) {
        Specification<Reply> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return replyRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Reply> findSearch(Map whereMap) {
        Specification<Reply> specification = createSpecification(whereMap);
        return replyRepository.findAll(specification);
    }

    @Override
    public Reply findById(String id) {
        return replyRepository.findById(id).get();
    }

    @Override
    public void add(Reply reply) {
        reply.setId(idWorker.nextId() + "");
        replyRepository.save(reply);
    }

    @Override
    public void update(Reply reply) {
        replyRepository.save(reply);
    }

    @Override
    public void deleteById(String id) {
        replyRepository.deleteById(id);
    }

    @Override
    public Specification<Reply> createSpecification(Map searchMap) {
        return new Specification<Reply>() {
            @Override
            public Predicate toPredicate(Root<Reply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // 编号
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 问题ID
                if (searchMap.get("problemId") != null && !"".equals(searchMap.get("problemId"))) {
                    predicateList.add(cb.like(root.get("problemId").as(String.class), "%" + (String) searchMap.get("problemId") + "%"));
                }
                // 回答内容
                if (searchMap.get("content") != null && !"".equals(searchMap.get("content"))) {
                    predicateList.add(cb.like(root.get("content").as(String.class), "%" + (String) searchMap.get("content") + "%"));
                }
                // 回答人ID
                if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + (String) searchMap.get("userId") + "%"));
                }
                // 回答人昵称
                if (searchMap.get("nickName") != null && !"".equals(searchMap.get("nickName"))) {
                    predicateList.add(cb.like(root.get("nickName").as(String.class), "%" + (String) searchMap.get("nickName") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

}
