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

//演示request保存作用域（demo01 和 demo02）
@WebServlet("/demo01")
public class Demo01Sevrlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向request保存作用域保存数据
        req.setAttribute("uname","lili");
        //2.客户端重定向
//        resp.sendRedirect("demo02");

        //3.服务器端重定向
        req.getRequestDispatcher("demo02").forward(req,resp);
    }
}
