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
@Table(name = "tb_article")
@Getter
@Setter
public class Article implements Serializable {

    @Id
    private String id;

    /**
     * 专栏ID
     */
    private String columnId;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 文章正文
     */
    private String content;
    /**
     * 文章封面
     */
    private String image;
    /**
     * 发表日期
     */
    private java.util.Date createTime;
    /**
     * 修改日期
     */
    private java.util.Date updateTime;
    /**
     * 是否公开
     */
    private String isPublic;
    /**
     * 是否置顶
     */
    private String isTop;
    /**
     * 浏览量
     */
    private Integer visits;
    /**
     * 点赞数
     */
    private Integer thumbUp;
    /**
     * 评论数
     */
    private Integer comment;
    /**
     * 审核状态
     */
    private String state;
    /**
     * 所属频道
     */
    private String channelId;
    /**
     * URL
     */
    private String url;
    /**
     * 类型
     */
    private String type;

}

