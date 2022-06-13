package com.xyxy.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
DAO:data(base) access object
封装了针对于数据表的通用的操作
 */
public abstract class BaseDao<T> {

    private Class<T> clazz = null;

    {
        //获取当前BaseDao的子类继承的父类中的泛型
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = parameterizedType.getActualTypeArguments();//获取了父类的泛型参数
        clazz = (Class<T>) typeArguments[0];//泛型的第一个参数
    }

    //通用的增删改操作 --version2.0 (考虑上事务)
    public int update(Connection conn, String sql, Object...args){//sql中占位符的个数与可变形参的长度相同！
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1,args[i]);//小心参数声明错误
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //修改其为自动提交数据
            //主要针对于使用数据库连接池的使用
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //3.资源的关闭
            JDBCUtils.closeResource(null,ps);
        }

        return 0;
    }

    //通用的查询操作，用于返回数据表中的一条记录--version2.0:考虑上事务
    public T getIstance(Connection conn, String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1,args[i]);
            }
            //执行返回结果集
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = metaData.getColumnCount();
            while(rs.next()){
                T t = clazz.newInstance();
                //处理结果集一行数据中的每一个列:给t对象指定的属性赋值
                for(int i = 0;i < columnCount;i++){
                    Object culumValue = rs.getObject(i + 1);

                    //获取每个列的列名
                    String columnName = metaData.getColumnName(i + 1);


                    //给cust对象指定的columnName属性，赋值为culumObject:通过反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,culumValue);

                }
                return t;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null,ps,rs);
        }


        return null;
    }

    //通用的查询操作，用于返回数据表中的多条记录构成的集合--version2.0:考虑上事务
    public List<T> getForList(Connection conn, String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1,args[i]);
            }
            //执行返回结果集
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData metaData = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = metaData.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while(rs.next()){
                T t = clazz.newInstance();
                //处理结果集一行数据中的每一个列:给t对象指定的属性赋值
                for(int i = 0;i < columnCount;i++){
                    Object culumValue = rs.getObject(i + 1);

                    //获取每个列的列名
                    String columnName = metaData.getColumnName(i + 1);


                    //给cust对象指定的columnName属性，赋值为culumObject:通过反射
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t,culumValue);

                }
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null,ps,rs);
        }


        return null;
    }

    //用于查询特殊值的通用方法
    public <E> E getValue(Connection conn,String sql,Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0;i < args.length;i++){
                ps.setObject(i + 1,args[i]);
            }

            rs = ps.executeQuery();
            if(rs.next()){
                return (E)rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.closeResource(null,ps,rs);
        }

        return null;
    }
}