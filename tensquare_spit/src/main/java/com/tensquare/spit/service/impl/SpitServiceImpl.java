package com.tensquare.spit.service.impl;

import com.tensquare.spit.entity.Spit;
import com.tensquare.spit.repository.SpitRepository;
import com.tensquare.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @author semiercq
 * @date 2020/11/15
 **/
@Service
public class SpitServiceImpl implements SpitService {

    @Autowired
    private SpitRepository spitRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Spit> findAll() {
        return spitRepository.findAll();
    }

    @Override
    public Spit findById(String id) {
        return spitRepository.findById(id).get();
    }

    @Override
    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishTime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbUp(0);
        spit.setComment(0);
        spit.setState("1");
        // 父节点更新
        if (spit.getParentId() != null && !"".equals(spit.getParentId())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentId()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitRepository.save(spit);
    }

    @Override
    public void update(Spit spit) {
        spitRepository.save(spit);
    }

    @Override
    public void deleteById(String id) {
        spitRepository.deleteById(id);
    }

    @Override
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitRepository.findByParentId(parentId, pageable);
    }

    @Override
    public void thumbUp(String spitId) {
//        方式一，效率低，先查，修改后再存
//        Spit spit = spitDao.findById(spitId).get();
//        spit.setThumbUp((spit.getThumbUp() == null ? 0 : spit.getThumbUp()) + 1);
//        spitDao.save(spit);

        // 使用原生mongo命令来实现自增，只有一次数据库交互操作
        // db.spit.update({"_id": "1"}, {$inc: {thumbUp:NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbUp", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
