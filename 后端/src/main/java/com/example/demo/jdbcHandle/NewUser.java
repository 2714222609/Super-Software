package com.example.demo.jdbcHandle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewUser {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/laf?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "loveyou1314";


    public static boolean Newuser(String name,String userid,String password,String contact) throws SQLException,
            ClassNotFoundException {
        Connection conn = null;
        Statement stmt = null;
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);

        // 打开链接
        System.out.println("连接数据库...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        // 执行查询
        System.out.println(" 实例化Statement对象...");
        stmt = conn.createStatement();
        String sql;
        sql = "insert into user values ("+"'"+userid+"'"+","+"'"+name+"'"+","+"'"+password+"'"+","+"'"+contact+"')";
        boolean rs = stmt.execute(sql);

        stmt.close();
        conn.close();
        rs=!rs;
        return rs ;
    }
}
