package com.example.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dzh
 * @since 2020-06-10
 */
public class User implements Serializable {
    private Long userId;
    private String userName;
    private Integer age;
    private Date birthday;
    private Double salary;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
