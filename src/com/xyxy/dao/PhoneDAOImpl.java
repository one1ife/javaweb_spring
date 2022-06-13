package com.xyxy.dao;

import com.xyxy.bean.Phone;

import java.sql.Connection;
import java.util.List;

public class PhoneDAOImpl extends BaseDao<Phone> implements PhoneDAO{

    Connection conn;

    {
        try {
            conn = JDBCUtils.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(Phone phone) {
        String sql = "insert into myphone values(?,?,?,?)";
        update(conn,sql,phone.getName(),phone.getPrice(),phone.getCount(),phone.getSoc());
    }

    @Override
    public List<Phone> getList() {
        String sql = "select * from myphone";
        List<Phone> list = getForList(conn, sql);
        return list;
    }

    @Override
    public List<Phone> getList(int page) {
        String sql = "select * from myphone limit ?,5";
        int pageNo = (page - 1) * 5;
        List<Phone> list = getForList(conn,sql,pageNo);
        return list;
    }

    @Override
    public List<Phone> getList(String keyword,int page) {
        String sql = "select * from myphone where name like ? or soc like ? limit ?,5";
        int pageNo = (page - 1) * 5;
        List<Phone> phoneList = getForList(conn, sql, "%" + keyword + "%", "%" + keyword + "%", pageNo);//通配符
        return phoneList;
    }

    @Override
    public void deleteByName(String name) {
        String sql = "delete from myphone where name = ?";
        update(conn,sql,name);
    }

    @Override
    public Phone getPhoneByName(String name) {
        String sql = "select * from myphone where name = ?";
        Phone phone = getIstance(conn,sql,name);
        return phone;
    }

    @Override
    public void updatePhone(Phone phone) {
        String sql = "update myphone set name = ? , price = ? , count = ? ,soc = ? where name = ?";
        update(conn,sql,phone.getName(),phone.getPrice(),phone.getCount(),phone.getSoc(),phone.getName());
    }

    @Override
    public int getPhoneCount() {
        String sql = "select count(*) from myphone";
        Object value = getValue(conn, sql);
        return Integer.parseInt(value.toString());
    }

    @Override
    public int getPhoneCount(String keyword) {
        String sql = "select count(*) from myphone where name like ? or soc like ?";
        Object value = getValue(conn, sql,"%" + keyword + "%","%" + keyword + "%");
        return Integer.parseInt(value.toString());
    }

}
