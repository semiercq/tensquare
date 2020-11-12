package com.tensquare.base.service.impl;

import com.tensquare.base.entity.Label;
import com.tensquare.base.repository.LabelRepository;
import com.tensquare.base.service.LabelService;
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

/**
 * @author semiercq
 * @date 2020/11/09
 **/
@Service
@Transactional
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    @Override
    public Label findById(String id) {
        return labelRepository.findById(id).get();
    }

    @Override
    public void add(Label label) {
        //设置id
        label.setId(idWorker.nextId() + "");
        labelRepository.save(label);
    }

    @Override
    public void update(Label label) {
        labelRepository.save(label);
    }

    @Override
    public void deleteById(String id) {
        labelRepository.deleteById(id);
    }

    @Override
    public List<Label> find(Label label) {
        return labelRepository.findAll(new Specification<Label>() {
            /**
             * 条件查询
             * @param root 根对象，也就是要把对象封装到哪个对象中。where 类名=label.getId
             * @param query 封装的都是查询的关键字，比如group by order by 等
             * @param cb 用来封装条件对象
             * @return 如果返回null表示不需要任何条件
             */
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // new一个list集合，来存放所有条件
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelName() != null && !"".equals(label.getLabelName())) {
                    // where labelName like "%小明%"
                    Predicate predicate = cb.like(root.get("labelName").as(String.class), "%" + label.getLabelName() + "%");
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    // where state = "1"
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return cb.and(parr);
            }
        });
    }

    @Override
    public Page<Label> pageQuery(Label label, int page, int size) {
        // 封装一个分页对象
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelRepository.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                // new一个list集合，来存放所有条件
                List<Predicate> list = new ArrayList<>();
                if (label.getLabelName() != null && !"".equals(label.getLabelName())) {
                    // where labelName like "%小明%"
                    Predicate predicate = cb.like(root.get("labelName").as(String.class), "%" + label.getLabelName() + "%");
                    list.add(predicate);
                }
                if (label.getState() != null && !"".equals(label.getState())) {
                    // where state = "1"
                    Predicate predicate = cb.equal(root.get("state").as(String.class), label.getState());
                    list.add(predicate);
                }
                Predicate[] parr = new Predicate[list.size()];
                list.toArray(parr);
                return cb.and(parr);
            }
        }, pageable);
    }


}
