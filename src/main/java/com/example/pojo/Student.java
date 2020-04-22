package com.example.pojo;

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

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
