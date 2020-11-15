package com.tensquare.spit.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author semiercq
 * @date 2020/11/15
 **/
@Setter
@Getter
@NoArgsConstructor
public class Spit {
    @Id
    private String _id;

    private String context;
    private Date publishTime;
    private String userId;
    private String nickName;
    private Integer visits;
    private Integer thumbUp;
    private Integer share;
    private Integer comment;
    private String state;
    private String parentId;
}
