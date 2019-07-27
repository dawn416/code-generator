/**
 * Copyright(C) 2019 Zhejiang Fline Technology Co., Ltd. All rights reserved.
 *
 */
package com.fline.generator.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * @since 2019年7月27日 上午7:28:11
 */
@XStreamAlias("typeConvert")
public class TypeConvert {
    @XStreamAsAttribute
    private String java;
    @XStreamAsAttribute
    private String jdbc;

    @Override
    public String toString() {
        return "TypeConvert [java=" + java + ", jdbc=" + jdbc + "]";
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getJdbc() {
        return jdbc;
    }

    public void setJdbc(String jdbc) {
        this.jdbc = jdbc;
    }

}
