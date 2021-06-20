package com.example.demo.jdbcHandle;

import java.sql.*;

public class selectItemsInformation {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/laf?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "loveyou1314";


    public static String selectitemInfo(String userid) throws SQLException, ClassNotFoundException {
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
        sql = "SELECT * FROM itemdetail where userId ="+"'"+userid+"'";
        ResultSet rs = stmt.executeQuery(sql);
        String info="";
        // 展开结果集数据库
        while(rs.next()){
            // 通过字段检索
            String title  = rs.getString("title");
            String detail = rs.getString("detail");
            String photos= rs.getString("photos");
            String userType= rs.getString("userType");
            String position= rs.getString("position");
            String time= rs.getString("time");
            String itemType= rs.getString("itemType");
            String tel= rs.getString("tel");
            String itemId= rs.getString("itemId");


            // 输出数据
            info=info+title+"+"+detail+"+"+photos+"+"+userType+"+"+position+"+"+time+"+"+itemType+"+"+tel+"+"+userid+
                    "+"+itemId+" ";
        }
        // 完成后关闭
//        System.out.println(info);
        rs.close();
        stmt.close();
        conn.close();
        return info;
    }
}
