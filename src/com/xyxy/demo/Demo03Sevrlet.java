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

//演示Session保存作用域（demo03 和 demo04）
@WebServlet("/demo03")
public class Demo03Sevrlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向Session保存作用域保存数据
        req.getSession().setAttribute("uname","lili");
        //2.客户端重定向
        resp.sendRedirect("demo04");
    }
}
