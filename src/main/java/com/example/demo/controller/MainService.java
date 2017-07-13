package com.example.demo.controller;

import com.example.demo.mapper.Db2Mapper;
import com.example.demo.mapper.Db3Mapper;
import com.example.demo.mapper.Db1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    Db3Mapper db3Mapper;

    @Autowired
    Db1Mapper db1Mapper;

    @Autowired
    Db2Mapper db2Mapper;


    public int getCount(){
        int count = db2Mapper.getUser();
        return count;
    }
}
