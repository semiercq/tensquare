package com.tensquare.article.entity;

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
@Table(name="tb_column")
@Getter
@Setter
public class Column implements Serializable {

    @Id
    private String id;
    /**
     * 专栏名称
     */
    private String name;
    /**
     * 专栏简介
     */
    private String summary;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 申请日期
     */
    private java.util.Date createTime;
    /**
     * 审核日期
     */
    private java.util.Date checkTime;
    /**
     * 状态
     */
    private String state;


}

