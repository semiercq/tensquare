package com.tensquare.article.service.impl;

import com.tensquare.article.entity.Channel;
import com.tensquare.article.repository.ChannelRepository;
import com.tensquare.article.service.ChannelService;
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
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Channel> findAll() {
        return channelRepository.findAll();
    }

    @Override
    public Page<Channel> findSearch(Map whereMap, int page, int size) {
        Specification<Channel> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return channelRepository.findAll(specification, pageRequest);
    }

    @Override
    public List<Channel> findSearch(Map whereMap) {
        Specification<Channel> specification = createSpecification(whereMap);
        return channelRepository.findAll(specification);
    }

    @Override
    public Channel findById(String id) {
        return channelRepository.findById(id).get();
    }

    @Override
    public void add(Channel channel) {
        channel.setId( idWorker.nextId()+"" );
        channelRepository.save(channel);
    }

    @Override
    public void update(Channel channel) {
        channelRepository.save(channel);
    }

    @Override
    public void deleteById(String id) {
        channelRepository.deleteById(id);
    }

    @Override
    public Specification<Channel> createSpecification(Map searchMap) {
        return new Specification<Channel>() {
            @Override
            public Predicate toPredicate(Root<Channel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 频道名称
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                    predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
