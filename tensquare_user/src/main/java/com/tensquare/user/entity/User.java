package com.tensquare.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author semiercq
 * @date 2020/11/19
 **/
@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    private String id;

    private String mobile;
    private String password;
    private String nickName;
    private String sex;
    private java.util.Date birthday;//出生年月日
    private String avatar;
    private String email;
    private java.util.Date regDate;//注册日期
    private java.util.Date updateDate;//修改日期
    private java.util.Date lastDate;//最后登陆日期
    private Long online;//在线时长（分钟）
    private String interest;//兴趣
    private String personality;//个性
    private Integer fansCount;//粉丝数
    private Integer followCount;//关注数


}
