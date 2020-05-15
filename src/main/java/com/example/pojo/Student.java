package com.example.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "student")
@Entity
@Table(name = "tab_student")
@org.hibernate.annotations.Table(appliesTo = "tab_student",comment = "这是一个表注释")
@Data
public class Student implements Serializable {
    @Id
//    @GeneratedValue//默认native @GeneratedValue()
    @GeneratedValue(generator = "x") //  使用uuid id的类型必须是String类型
    @GenericGenerator(name = "x", strategy = "uuid") // 使用hibernate的uuid策略
    private String id;
//    private Integer id;
    @Column(name = "tab_name",length = 50)
    private String name;
    @Column(name = "tab_age",length = 50)
    private String age;
    @Column(name = "tab_sex",length = 50)
    private String sex;
    @Column(name = "tab_content",length = 50)
    private String content;
    @Column(name = "phone",length = 20)
    private String phone;

}
