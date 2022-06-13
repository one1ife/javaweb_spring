package com.xyxy.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Jacky Zou
 * @Date 2022/5/9 10:30
 * @Version 1.0
 */

@WebServlet("/demo02")
public class Demo02Sevrlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取request保存作用域保存数据，key为uname
        Object uname = req.getAttribute("uname");
        System.out.println(uname);
    }
}
