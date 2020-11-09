package com.tensquare.base.service;

import com.tensquare.base.entity.Label;
import entity.Result;

import java.util.List;

/**
 * @author semiercq
 * @date 2020/11/09
 **/
public interface LabelService {

    /**
     * 查询全部标签
     *
     * @return List
     */
    public List<Label> findAll();

    /**
     * 根据id查询标签
     *
     * @param id id
     * @return Label
     */
    public Label findById(String id);

    /**
     * 增加标签
     *
     * @param label label
     */
    public void add(Label label);

    /**
     * 修改标签
     *
     * @param label label
     */
    public void update(Label label);

    /**
     * 删除标签
     *
     * @param id id
     */
    public void deleteById(String id);

}
