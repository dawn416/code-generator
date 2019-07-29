package com.fline.dao;

import com.fline.entity.BigMatch;

public interface BigMatchMapper {
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKey(BigMatch bigMatch);
    
    int insert(BigMatch bigMatch);

}