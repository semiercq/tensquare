package com.tensquare.spit.service;

import com.tensquare.spit.entity.Spit;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author semiercq
 * @date 2020/11/15
 **/
public interface SpitService {

    /**
     * 查找全部
     *
     * @return list
     */
    List<Spit> findAll();

    /**
     * 根据ID查找
     *
     * @param id id
     * @return Spit
     */
    Spit findById(String id);

    /**
     * 保存
     *
     * @param spit page
     */
    void save(Spit spit);

    /**
     * 更新
     *
     * @param spit spit
     */
    void update(Spit spit);

    /**
     * 删除
     *
     * @param id id
     */
    void deleteById(String id);

    /**
     * 根据上级ID分页查找吐槽数据
     *
     * @param parenTid parenTid
     * @param page     page
     * @param size     size
     * @return Page
     */
    Page<Spit> findByParentId(String parenTid, int page, int size);

    /**
     * 点赞
     *
     * @param spitId spitId
     */
    void thumbUp(String spitId);
}
