package com.tensquare.friend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @author semiercq
 * @date 2020/11/23
 **/
@Entity
@Table(name = "tb_friend")
@IdClass(Friend.class)
@Getter
@Setter
public class Friend implements Serializable {

    @Id
    private String userId;

    @Id
    private String friendId;

    private String isLike;

}