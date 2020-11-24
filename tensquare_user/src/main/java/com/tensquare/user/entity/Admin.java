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
@Table(name = "tb_admin")
public class Admin implements Serializable {

    @Id
    private String id;


    private String loginName;
    private String password;
    private String state;


}
