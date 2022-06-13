package com.xyxy.service.impl;

import com.xyxy.bean.Phone;
import com.xyxy.service.PhoneService;
import com.xyxy.dao.PhoneDAO;
import com.xyxy.dao.PhoneDAOImpl;

import java.util.List;

public class PhoneServiceImpl implements PhoneService {

    private PhoneDAO phoneDAO = null;

    @Override
    public List<Phone> getPhoneLIst(String keyword, int page) {
        return phoneDAO.getList(keyword,page);
    }

    @Override
    public void addPhone(Phone phone) {
        phoneDAO.insert(phone);
    }

    @Override
    public Phone getPhoneByName(String name) {
        return phoneDAO.getPhoneByName(name);
    }

    @Override
    public void updatePhone(Phone phone) {
        phoneDAO.updatePhone(phone);
    }

    @Override
    public void deleteByName(String name) {
        phoneDAO.deleteByName(name);
    }

    @Override
    public int getPageCount(String keyword) {
        int count = phoneDAO.getPhoneCount(keyword);
        int pageCount = (count + 5 - 1) / 5;
        return pageCount;
    }
}
