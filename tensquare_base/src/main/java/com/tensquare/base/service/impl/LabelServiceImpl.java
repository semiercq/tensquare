package com.tensquare.base.service.impl;

import com.tensquare.base.entity.Label;
import com.tensquare.base.repository.LabelRepository;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.transaction.Transactional;
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
}
