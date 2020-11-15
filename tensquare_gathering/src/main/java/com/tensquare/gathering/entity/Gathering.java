package com.tensquare.gathering.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author semiercq
 * @date 2020/11/13
 **/
@Entity
@Table(name = "tb_gathering")
@Getter
@Setter
public class Gathering implements Serializable {

    @Id
    private String id;

    /**
     * 活动名称
     */
    private String name;
    /**
     * 大会简介
     */
    private String summary;
    /**
     * 详细说明
     */
    private String detail;
    /**
     * 主办方
     */
    private String sponsor;
    /**
     * 活动图片
     */
    private String image;
    /**
     * 开始时间
     */
    private java.util.Date startTime;
    /**
     * 截止时间
     */
    private java.util.Date endTime;
    /**
     * 举办地点
     */
    private String address;
    /**
     * 报名截止
     */
    private java.util.Date enrollTime;
    /**
     * 是否可见
     */
    private String state;
    /**
     * 城市
     */
    private String city;

}
