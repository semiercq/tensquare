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
@Table(name = "tb_channel")
@Getter
@Setter
public class Channel implements Serializable {

    @Id
    private String id;
    /**
     * 频道名称
     */
    private String name;
    /**
     * 状态
     */
    private String state;


}

