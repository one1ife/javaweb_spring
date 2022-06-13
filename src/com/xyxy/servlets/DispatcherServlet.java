package com.xyxy.servlets;

import com.xyxy.ioc.BeanFactory;
import com.xyxy.ioc.ClassPathXmlApplicationContext;
import com.xyxy.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
/**
 * @Author Jacky Zou
 * @Date 2022/6/1 9:41
 * @Version 1.0
 */

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;


    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
        beanFactory = new ClassPathXmlApplicationContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");

        //假设url是：http://localhost:8080/pro09/hello.do
        //那么servletPath 是 hello.do
        //我的思路是：
        //第1步：  /hello.do  -> hello  或者 /phone.do  -> phone
        //hello -> HelloController  或者 phone -> PhoneController
        String servletPath = req.getServletPath();
//        System.out.println("servletPath" + servletPath);
        servletPath = servletPath.substring(1);
        int lastIndexOf = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);
//        System.out.println("servletPath:" + servletPath);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        //设置编码
        req.setCharacterEncoding("UTF-8");

        String operate = req.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods){
                if (operate.equals(method.getName())){
                    //1.统一获取请求参数

                    //获取当前方法的参数,返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //parametersValues 用来承载参数的值
                    Object[] parametersValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        //如果参数名是req,resp,session 那么就不是通过请求中获取参数的方式了
                        if("req".equals(parameterName)){
                            parametersValues[i] = req;
                        }else if ("resp".equals(parameterName)){
                            parametersValues[i] = resp;
                        }else if("session".equals(parameterName)){
                            parametersValues[i] = req.getSession();
                        }else {
                            //从请求中获取参数值
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parmameterObj = parameterValue;

                            if (parmameterObj != null) {

                                if ("java.lang.Integer".equals(typeName) || "int".equals(typeName)) {
                                    parmameterObj = Integer.parseInt(parameterValue);
                                }

                            }
                            parametersValues[i] = parmameterObj;
                        }

                    }
                    //2.controller组件中的方法调用
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj, parametersValues);

                    //3.视图处理
                    String methodReturnStr = (String) returnObj;
                    if(methodReturnStr.startsWith("redirect:")){  //比如 redirect:phone.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    }else {
                        super.processTemplate(methodReturnStr,req,resp); //比如：“edit"
                    }
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}

// 常见错误：java.lang.IllegalArgumentException: argument type mismatch