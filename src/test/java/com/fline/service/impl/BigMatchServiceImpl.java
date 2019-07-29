package com.fline.service.impl;

import com.fline.dao.BigMatchMapper;
import com.fline.entity.BigMatch;
import com.fline.service.BigMatchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;

//@Service
public class BigMatchServiceImpl implements BigMatchService {

//    @Autowired
    private BigMatchMapper bigMatchMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bigMatchMapper.deleteByPrimaryKey(id);
    }
    
    @Override
    public int updateByPrimaryKey(BigMatch bigMatch) {
        return bigMatchMapper.updateByPrimaryKey(bigMatch);
    }
    
    @Override
    public int insert(BigMatch bigMatch) {
        return bigMatchMapper.insert(bigMatch);
    }
}