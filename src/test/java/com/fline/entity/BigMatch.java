package com.fline.entity;

import java.io.Serializable;

import java.util.Date;

//import lombok.Data;

//@Data
public class BigMatch implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private Integer areaId;
    /**
     * 
     */
    private String areaName;
    /**
     * 
     */
    private Integer subject_Id;
    /**
     * 
     */
    private String subjectName;
    /**
     * 
     */
    private String banner;
    /**
     * 
     */
    private Date starttime;
    /**
     * 
     */
    private Date endtime;
    /**
     * 
     */
    private String memo;

}