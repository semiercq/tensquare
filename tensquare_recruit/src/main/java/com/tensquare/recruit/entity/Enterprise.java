package com.tensquare.recruit.entity;

/**
 * @author semiercq
 * @date 2020/11/12
 **/

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_enterprise")
@Setter
@Getter
public class Enterprise implements Serializable {

    @Id
    private String id;
    /**
     * 企业名称
     */
    private String name;
    /**
     * 企业简介
     */
    private String summary;
    /**
     * 企业地址
     */
    private String address;
    /**
     * 标签列表
     */
    private String labels;
    /**
     * 坐标
     */
    private String coordinate;
    /**
     * 是否热门 0：非热门 1：热门
     */
    private String isHot;
    /**
     * LOGO
     */
    private String logo;
    /**
     * 职位数
     */
    private Integer joCount;
    /**
     * URL
     */
    private String url;

}
