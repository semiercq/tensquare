package com.tensquare.qa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author semiercq
 * @date 2020/11/10
 **/
@Entity
@Table(name = "tb_reply")
@Getter
@Setter
public class Reply implements Serializable {
    @Id
    private String id;
    /**
     * 问题ID
     */
    private String problemId;
    /**
     * 回答内容
     */
    private String content;
    /**
     * 创建日期
     */
    private java.util.Date createTime;
    /**
     * 更新日期
     */
    private java.util.Date updateTime;
    /**
     * 回答人ID
     */
    private String userId;
    /**
     * 回答人昵称
     */
    private String nickName;
}
