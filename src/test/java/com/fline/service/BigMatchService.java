package com.fline.service;

import com.fline.entity.BigMatch;

public interface BigMatchService {
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKey(BigMatch bigMatch);
    
    int insert(BigMatch bigMatch);
}