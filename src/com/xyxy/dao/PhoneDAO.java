package com.xyxy.dao;

import com.xyxy.bean.Phone;

import java.sql.Connection;

import java.util.List;

/*
此接口用于规范正对于myphone表的常用操作
 */
public interface PhoneDAO {

    //添加
    void insert(Phone phone);

    //查询全部
    List<Phone> getList();

    //获取指定页码的库存列表信息
    List<Phone> getList(int page);

    //查询关键字
    List<Phone> getList(String keyword,int page);

    //针对指定的name,删除表中的一条记录
    void deleteByName(String name);

    //针对指定的id查询得到对应的Phone对象
    Phone getPhoneByName(String name);

    //修改指定的库存记录
    void updatePhone(Phone phone);

    //查询库存总记录条数
    int getPhoneCount();

    //查询库存总记录条数
    int getPhoneCount(String keyword);

}
