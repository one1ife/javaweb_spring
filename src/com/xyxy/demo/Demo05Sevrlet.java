package com.xyxy.demo;

import javax.servlet.ServletContext;
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

//演示Application保存作用域（demo05 和 demo06）
@WebServlet("/demo05")
public class Demo05Sevrlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.向Application保存作用域保存数据
        //ServletContext : Servlet上下文
        ServletContext application = req.getServletContext();
        application.setAttribute("uname","lili");
        //2.客户端重定向
        resp.sendRedirect("demo06");
    }
}
