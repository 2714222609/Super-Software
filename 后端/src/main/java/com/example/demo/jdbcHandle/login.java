package com.example.demo.jdbcHandle;
import java.sql.*;
public class login {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/laf?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "loveyou1314";


    public static boolean Check(String userid,String password) throws SQLException, ClassNotFoundException {
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
        sql = "SELECT * FROM user where userId ="+"'"+userid+"'"+"and passWord="+"'"+password+"'";
        ResultSet rs = stmt.executeQuery(sql);
        String id = null,pd=null;
        boolean flag=false;
        // 展开结果集数据库
        while(rs.next()){
        // 通过字段检索
         id  = rs.getString("userId");
//                String name = rs.getString("name");
         pd = rs.getString("passWord");


        // 输出数据
        System.out.print("ID: " + id);
//                System.out.print(", 站点名称: " + name);
        System.out.print(", password: " + pd);
        System.out.print("\n");
            }
        // 完成后关闭
        rs.close();
        stmt.close();
        conn.close();
        if (id!=null&&pd!=null){
            flag=id.equals(userid)&&pd.equals(password);
        }
        return flag ;
    }
}
