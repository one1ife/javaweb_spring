package com.xyxy.controllers;

import com.xyxy.bean.Phone;
import com.xyxy.service.PhoneService;
import com.xyxy.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
/**
 * @Author Jacky Zou
 * @Date 2022/5/31 21:58
 * @Version 1.0
 */


public class PhoneController{

    private PhoneService phoneService = null;

    private String index(String oper,String keyword,Integer page,HttpServletRequest req){

        HttpSession session = req.getSession();

        if(page == null){
            page = 1;
        }

        //如果oper不为null,说明通过表单的查询按钮点击过来的
        //如果oper为空，说明不是通过表单的查询按钮点击过来的
        //如果keyword为null,需要设置为空字符串“”，否则查询时会拼接成 %null%,我们期望的是%%
        if(StringUtil.isNotEnpty(oper) && "search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，page应该还原为1，keyword应该从请求参数中获取
            page = 1;
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }

            //将keyword保存（覆盖）到session中
            session.setAttribute("keyword",keyword);
        }else {
            //如果不是点击的查询按钮，那么查询是基于session中保存的现有的session进行查询
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }

        //重新更新当前页的值
        session.setAttribute("page",page);

        List<Phone> phoneList = phoneService.getPhoneLIst(keyword,page);
        //保存session作用域
        session.setAttribute("phoneList",phoneList);
        //总页数
        int pageCount = phoneService.getPageCount(keyword);

        //保存作用域
        session.setAttribute("pageCount",pageCount);

        //此处视图名称是 index
        //那么thymeleaf会将这个逻辑视图名称 对应到 物理视图 名称上去
        //逻辑视图名称 ： index
        //物理视图名   ： view-prefix + 逻辑视图名称 + view-suffix
        //所以 真实的视图名称是： / index  .html
//        super.processTemplate("index",req,resp);
        return "index";
    }

    private String add(String name,int price,int count,String soc){
        Phone phone = new Phone(name, price, count, soc);
        phoneService.addPhone(phone);
//        resp.sendRedirect("phone.do");
        return "redirect:phone.do";
    }

    private String del(String name){
        phoneService.deleteByName(name);

//        resp.sendRedirect("phone.do");
        return "redirect:phone.do";
    }

    private String edit(String name,HttpServletRequest req){
        Phone phone = phoneService.getPhoneByName(name);
        req.setAttribute("phone",phone);
//        super.processTemplate("edit",req,resp);
        return "edit";

    }

    private String update(String name,int price,int count,String soc){
        phoneService.updatePhone(new Phone(name, price,count,soc));

        //资源跳转
//        resp.sendRedirect("phone.do");
        return "redirect:phone.do";
    }
}
