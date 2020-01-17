package com.rjh.jpa.entity;

import com.rjh.jpa.config.InjectEntityListener;
import com.rjh.jpa.config.InjectProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Null
 * @date 2020-01-13
 */
@Data
@Entity
@EntityListeners({ InjectEntityListener.class})
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column
    @InjectProperty(value="sex")
    private Boolean sex;

//    @CreatedDate
    @InjectProperty(value = "now",insert = true)
    @Column(nullable = false, updatable = false) // 这个很重要，一定要配置
    private Date createTime;

    @InjectProperty(value = "now")
    @Column(nullable = false)
    private Date updateTime;
    /**
     * 创建人
     */
    @InjectProperty(value = "userId",insert = true)
    @Column(nullable = false, updatable = false)
    private String createUser;
    /**
     * 修改人
     */
    @InjectProperty(value = "userId")
    @Column(nullable = false)
    private String updateUser;
}
