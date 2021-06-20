package com.example.demo.Controller;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

import com.example.demo.jdbcHandle.login;
import com.example.demo.jdbcHandle.selectPersonalInformation;
import com.example.demo.jdbcHandle.uploadItemInfomation;
import com.example.demo.jdbcHandle.selectItemsInformation;
import com.example.demo.jdbcHandle.deleteItem;
import com.example.demo.jdbcHandle.getinfobyid;
import com.example.demo.jdbcHandle.NewUser;
import com.example.demo.jdbcHandle.Change;


@Controller
@MultipartConfig
public class HelloController extends HttpServlet {
    @ResponseBody
    @RequestMapping("/home")
    public void longin(String userid,String password, HttpServletResponse response) throws ServletException,
            IOException, SQLException, ClassNotFoundException {
        System.out.println("连接成功");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        System.out.println("userid="+userid);
        System.out.println("password="+password);
        Writer out=response.getWriter();
        if (login.Check(userid,password)){
            out.write('1');
            out.flush();
        }else{
            out.write('0');
            out.flush();
        }

    }
    @RequestMapping("/info")
    public void personalInfo(String userid,String password, HttpServletResponse response,HttpServletRequest request) throws ServletException,
            IOException, SQLException, ClassNotFoundException{
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        System.out.println(userid+" "+password);
        String[] personInfo=selectPersonalInformation.selectInfo(userid, password);
        System.out.println(personInfo[0]);
        Writer out=response.getWriter();
        out.write(personInfo[0]+" "+personInfo[1]+" "+personInfo[2]);
        out.flush();


    }
    @RequestMapping("/upload")
    public void uploadItemInfo(String title, String detail, String photos, String userType, String position,
                               String time, String itemType,String tel, String userId, String itemId,
                               HttpServletResponse response)throws ServletException,
            IOException, SQLException, ClassNotFoundException{
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        System.out.println( );
        Writer out=response.getWriter();
        boolean flag =uploadItemInfomation.uploadInfo(title,detail,photos,userType,position,time,itemType,tel,
                userId,
                itemId);
        out.write(String.valueOf(flag));
        out.flush();
    }
    @RequestMapping("/getinfo")
    public void getitemsInfo(String userid, HttpServletResponse response, HttpServletRequest request) throws ServletException,
            IOException, SQLException, ClassNotFoundException{
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        System.out.println(userid);
        String infos=selectItemsInformation.selectitemInfo(userid);
        Writer out=response.getWriter();
        out.write(infos);
        out.flush();

    }
    @RequestMapping("/delete")
    public void deleteinformation(String itemid,HttpServletResponse response) throws ServletException,
            IOException, SQLException, ClassNotFoundException {
        System.out.println("连接成功");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        System.out.println("itemid="+itemid);
        boolean flag=deleteItem.delete(itemid);
        Writer out=response.getWriter();
        out.write(String.valueOf(flag));
        out.flush();
    }
    @RequestMapping("/search")
    public void getInfobyitemid(String itemtype, HttpServletResponse response, HttpServletRequest request) throws ServletException,
            IOException, SQLException, ClassNotFoundException{
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        System.out.println(itemtype);
        String infos=getinfobyid.selectitemInfobyid(itemtype);
        System.out.println(infos);
        Writer out=response.getWriter();
        out.write(infos);
        out.flush();

    }
    @RequestMapping("/register")
    public void register(String name,String userid,String password,String contact,HttpServletResponse response) throws ServletException,
            IOException, SQLException, ClassNotFoundException{
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","GET,POST");
        boolean flag=NewUser.Newuser(name,userid,password,contact);
        Writer out=response.getWriter();
        out.write(String.valueOf(flag));
        out.flush();

    }
    @RequestMapping("/change")
    public void changeinformation(String index,String userid,String changes,HttpServletResponse response) throws ServletException,
            IOException, SQLException, ClassNotFoundException {
        System.out.println("连接成功");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        String sql="";
        if (index.equals("0")){
            sql="update user set userName = "+"'"+changes+"'"+"where userId = "+"'"+userid+"'";

        }else {
            sql="update user set Contact = "+"'"+changes+"'"+"where userId = "+"'"+userid+"'";
        }
        boolean flag=Change.changeInfo(sql);
        Writer out=response.getWriter();
        out.write(String.valueOf(flag));
        out.flush();
    }
}