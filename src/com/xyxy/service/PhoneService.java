package com.xyxy.service;

import com.xyxy.bean.Phone;

import java.util.List;

public interface PhoneService {
    //获取指定库存列表信息
    List<Phone> getPhoneLIst(String keyword,int page);
    //添加库存呢记录信息
    void addPhone(Phone phone);
    //根据name查看指定库存记录
    Phone getPhoneByName(String name);
    //修改指定的库存记录
    void updatePhone(Phone phone);
    //删除指定库存记录
    void deleteByName(String name);
    //获取总页数
    int getPageCount(String keyword);
}
