package com.hx.demo.student.entity;

    import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* <p>
    * 表注释
    * </p>
*
* @author 杜志红
* @since 2020-05-14
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class TabStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tabAge;

    private String tabContent;

    private String tabName;

    private String tabSex;

    private String phone;


}
